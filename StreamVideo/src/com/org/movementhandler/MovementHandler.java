package com.org.movementhandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import com.cellbots.CellbotProtos;
import com.cellbots.CellbotProtos.ControllerState;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

/*
 * This is a class to store the state of the robot.
 */
public class MovementHandler extends Thread {

	private Handler comHandler;
	public boolean listening = false;
	public static String TAG = "MovementHandler";
	public static String ROBOT_ID = "jason's";
	private CellbotProtos.PhoneState.Builder stateb;
	private CellbotProtos.PhoneState statephone;
	HttpClient httpclient;
	InetSocketAddress clientAddress = null;
	ControllerState controllerState;
	long lastControllerTimeStamp = 0;
	public Handler handler;

	public MovementHandler(Handler cHandler) {
		comHandler = cHandler;
		stateb = CellbotProtos.PhoneState.newBuilder();
		stateb.setBotID(ROBOT_ID);
		stateb.setTimestamp(System.currentTimeMillis());
		statephone = stateb.build();
	}

	@Override
	public void run() {
		try {
			Looper.prepare();
			handler = new Handler() {

				@Override
				public void handleMessage(Message msg) {

					try {

						httpclient = new DefaultHttpClient();

						HttpPost post = new HttpPost(
								"http://192.168.1.203:8888/robotstate");
						post.setEntity(new ByteArrayEntity(statephone
								.toByteArray()));
						HttpResponse resp = httpclient.execute(post);
						HttpEntity ent = resp.getEntity();

						if (ent == null)
							return;
						InputStream resStream = ent.getContent();
						ControllerState cs = ControllerState
								.parseFrom(resStream);

						String txt = processControllerStateEvent(cs);

						if (cs != null
								&& cs.getTimestamp() != lastControllerTimeStamp) {
							if (cs.hasTxtCommand()) {
								lastControllerTimeStamp = cs.getTimestamp();
							} else if (txt != null) {
								lastControllerTimeStamp = cs.getTimestamp();
							}

						}

					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					} catch (IllegalStateException e) {
						e.printStackTrace();
					} catch (com.google.protobuf.InvalidProtocolBufferException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					} catch (NullPointerException e) {
						Log.e(TAG, "npe", e);
						e.printStackTrace();
					}

				}

				private String processControllerStateEvent(ControllerState cs) {
					for (ControllerState.KeyEvent ev : cs.getKeyEventList()) {
						if (ev.getKeyDown()) {
							Log.e("Movement", ev.getKeyCode());
							String code = ev.getKeyCode();
							if (code.substring(0, code.indexOf(':')).equals(
									"com")) {
								Message msg = comHandler.obtainMessage(0,
										Integer.parseInt(code.substring(code
												.indexOf(':') + 1, code
												.length())), 0);
								msg.sendToTarget();
							}
						}

						if (ev.getKeyUp()) {
							Log.e("Movement", ev.getKeyCode());
						}
					}
					return "";
				}

				/*
				 * private void processKeyUpEvent(int parseInt) {
				 * 
				 * }
				 * 
				 * private String processKeyDownEvent(int parseInt) { return
				 * null; }
				 */
			};

			Looper.loop();

			Log.i(TAG, "Thread exiting gracefully");
		} catch (Throwable t) {
			Log.e(TAG, "Thread halted due to an error", t);
		}
	}

}
