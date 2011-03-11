package com.org.video.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ErrorEvent;
import com.google.gwt.event.dom.client.ErrorHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;

public class videoLayout implements EntryPoint {

	@SuppressWarnings("unused")
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";
	public static String test = "Test";
	static String VIDEO_URL = "/video";
	public static String BOT_ID = "jason's";
	private final EventListenerServiceAsync eventService = GWT
			.create(EventListenerService.class);

	public void onModuleLoad() {
		Timer elapsedTimer;
		RootPanel rootPanel = RootPanel.get();
		Window.setTitle("Androbot's Console");
		VerticalPanel verticalPanel = new VerticalPanel();
		verticalPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		rootPanel.add(verticalPanel, 10, 10);
		verticalPanel.setSize("320px", "255px");

		final Image image = new Image("/video");
		verticalPanel.add(image);
		image.setSize("320px", "240px");

		final Label lblLag = new Label("Lag :");
		verticalPanel.add(lblLag);
		lblLag.setWidth("151px");
		verticalPanel.setCellVerticalAlignment(lblLag,
				HasVerticalAlignment.ALIGN_BOTTOM);

		HorizontalPanel horizontalPanel_1 = new HorizontalPanel();
		verticalPanel.add(horizontalPanel_1);
		verticalPanel.setCellVerticalAlignment(horizontalPanel_1,
				HasVerticalAlignment.ALIGN_BOTTOM);
		horizontalPanel_1.setSize("315px", "19px");

		Label lblCommpressionLevel = new Label(
				"Commpression Level : (0 to 100)");
		horizontalPanel_1.add(lblCommpressionLevel);
		horizontalPanel_1.setCellVerticalAlignment(lblCommpressionLevel,
				HasVerticalAlignment.ALIGN_MIDDLE);
		lblCommpressionLevel.setSize("201px", "15px");

		final TextBox textBox_1 = new TextBox();
		horizontalPanel_1.add(textBox_1);
		horizontalPanel_1.setCellHorizontalAlignment(textBox_1,
				HasHorizontalAlignment.ALIGN_CENTER);
		horizontalPanel_1.setCellVerticalAlignment(textBox_1,
				HasVerticalAlignment.ALIGN_MIDDLE);
		textBox_1.setWidth("45px");

		Button btnSet = new Button("set");
		btnSet.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				String text = textBox_1.getText();
				if (text != null) {
					int value = Integer.parseInt(text);
					if (value > 0 && value < 100) {
						eventService.handleMouseDown("com:" + value, BOT_ID,
								new AsyncCallback<String>() {
									@Override
									public void onSuccess(String result) {
									}
									@Override
									public void onFailure(Throwable caught) {
									}
								});
					}
				}
			}
		});
		horizontalPanel_1.add(btnSet);
		horizontalPanel_1.setCellHorizontalAlignment(btnSet,
				HasHorizontalAlignment.ALIGN_RIGHT);
		horizontalPanel_1.setCellVerticalAlignment(btnSet,
				HasVerticalAlignment.ALIGN_MIDDLE);

		VerticalPanel verticalPanel_1 = new VerticalPanel();
		rootPanel.add(verticalPanel_1, 10, 324);
		verticalPanel_1.setSize("346px", "281px");

		HorizontalPanel horizontalPanel = new HorizontalPanel();
		verticalPanel_1.add(horizontalPanel);
		horizontalPanel.setSize("321px", "50px");

		Button btnNewButton = new Button("Forward");
		btnNewButton.addMouseUpHandler(new MouseUpHandler() {
			public void onMouseUp(MouseUpEvent event) {
				eventService.handleMouseUp("stop", BOT_ID,
						new AsyncCallback<String>() {

							@Override
							public void onSuccess(String result) {
							}

							@Override
							public void onFailure(Throwable caught) {
							}
						});
			}
		});
		btnNewButton.addMouseDownHandler(new MouseDownHandler() {
			public void onMouseDown(MouseDownEvent event) {
				eventService.handleMouseDown("forward", BOT_ID,
						new AsyncCallback<String>() {
							@Override
							public void onSuccess(String result) {
							}

							@Override
							public void onFailure(Throwable caught) {
							}
						});
			}
		});
		horizontalPanel.add(btnNewButton);
		horizontalPanel.setCellHorizontalAlignment(btnNewButton,
				HasHorizontalAlignment.ALIGN_CENTER);
		btnNewButton.setSize("192px", "56px");

		FlexTable flexTable = new FlexTable();
		verticalPanel_1.add(flexTable);
		flexTable.setSize("330px", "71px");

		Button btnNewButton_1 = new Button("Left");
		btnNewButton_1.addMouseDownHandler(new MouseDownHandler() {
			public void onMouseDown(MouseDownEvent event) {
				eventService.handleMouseDown("left", BOT_ID,
						new AsyncCallback<String>() {
							@Override
							public void onSuccess(String result) {
							}

							@Override
							public void onFailure(Throwable caught) {
							}
						});
			}
		});
		btnNewButton_1.addMouseUpHandler(new MouseUpHandler() {
			public void onMouseUp(MouseUpEvent event) {
				eventService.handleMouseUp("stop", BOT_ID,
						new AsyncCallback<String>() {
							@Override
							public void onSuccess(String result) {
							}

							@Override
							public void onFailure(Throwable caught) {
							}
						});
			}
		});
		flexTable.setWidget(0, 0, btnNewButton_1);
		btnNewButton_1.setSize("58px", "50px");

		Button btnNewButton_2 = new Button("Backward");
		btnNewButton_2.addMouseDownHandler(new MouseDownHandler() {
			public void onMouseDown(MouseDownEvent event) {
				eventService.handleMouseDown("back", BOT_ID,
						new AsyncCallback<String>() {

							@Override
							public void onSuccess(String result) {
							}

							@Override
							public void onFailure(Throwable caught) {
							}
						});
			}
		});
		btnNewButton_2.addMouseUpHandler(new MouseUpHandler() {
			public void onMouseUp(MouseUpEvent event) {
				eventService.handleMouseUp("stop", BOT_ID,
						new AsyncCallback<String>() {

							@Override
							public void onSuccess(String result) {
							}

							@Override
							public void onFailure(Throwable caught) {
							}
						});
			}
		});
		flexTable.setWidget(0, 1, btnNewButton_2);
		btnNewButton_2.setSize("91px", "50px");

		Button btnNewButton_3 = new Button("Right");
		btnNewButton_3.addMouseDownHandler(new MouseDownHandler() {
			public void onMouseDown(MouseDownEvent event) {
				eventService.handleMouseDown("right", BOT_ID,
						new AsyncCallback<String>() {

							@Override
							public void onSuccess(String result) {
							}

							@Override
							public void onFailure(Throwable caught) {
							}
						});
			}
		});
		btnNewButton_3.addMouseUpHandler(new MouseUpHandler() {
			public void onMouseUp(MouseUpEvent event) {
				eventService.handleMouseUp("stop", BOT_ID,
						new AsyncCallback<String>() {

							@Override
							public void onSuccess(String result) {
							}

							@Override
							public void onFailure(Throwable caught) {
							}
						});
			}
		});
		flexTable.setWidget(0, 2, btnNewButton_3);
		btnNewButton_3.setSize("72px", "51px");
		flexTable.getCellFormatter().setHorizontalAlignment(0, 0,
				HasHorizontalAlignment.ALIGN_CENTER);
		flexTable.getCellFormatter().setHorizontalAlignment(0, 1,
				HasHorizontalAlignment.ALIGN_CENTER);
		flexTable.getCellFormatter().setHorizontalAlignment(0, 2,
				HasHorizontalAlignment.ALIGN_CENTER);

		final Label lblNewLabel = new Label("Status");

		Button btnSetFocusFor = new Button("Set Focus for KeyBoard Control");
		btnSetFocusFor.addKeyDownHandler(new KeyDownHandler() {
			public void onKeyDown(KeyDownEvent event) {
				lblNewLabel.setText(event.getNativeKeyCode() + " "
						+ KeyCodes.KEY_ENTER);
			}
		});
		btnSetFocusFor.addKeyUpHandler(new KeyUpHandler() {
			public void onKeyUp(KeyUpEvent event) {
				lblNewLabel.setText(event.getNativeKeyCode() + " "
						+ KeyCodes.KEY_ENTER);
			}
		});

		flexTable.setWidget(1, 1, btnSetFocusFor);
		btnSetFocusFor.setSize("192px", "53px");

		FlexTable flexTable_1 = new FlexTable();
		verticalPanel_1.add(flexTable_1);

		Label lblCommand = new Label("Command :");
		flexTable_1.setWidget(0, 0, lblCommand);
		lblCommand.setWidth("69px");

		final TextBox textBox = new TextBox();
		textBox.addKeyDownHandler(new KeyDownHandler() {

			@Override
			public void onKeyDown(KeyDownEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					String text = textBox.getText();
					textBox.setText("");
					String action = text.substring(text.indexOf(':') + 1, text
							.length());
					text = text.substring(0, text.indexOf(':'));
					if (text.equals("email")) {
						eventService.sendEmail(action, BOT_ID,
								new AsyncCallback<String>() {
									@Override
									public void onSuccess(String result) {
										lblNewLabel
												.setText("Update sent via Email");
									}

									@Override
									public void onFailure(Throwable caught) {
										lblNewLabel.setText("Failed to send");
									}
								});
					} else if (text.equals("sms")) {

					} else {

					}
				}
			}
		});

		flexTable_1.setWidget(0, 1, textBox);
		textBox.setWidth("188px");

		Button btnSend = new Button("Send");
		btnSend.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
			}
		});
		flexTable_1.setWidget(0, 2, btnSend);
		flexTable_1.getCellFormatter().setHorizontalAlignment(0, 0,
				HasHorizontalAlignment.ALIGN_CENTER);
		flexTable_1.getCellFormatter().setVerticalAlignment(0, 0,
				HasVerticalAlignment.ALIGN_MIDDLE);

		verticalPanel_1.add(lblNewLabel);
		verticalPanel_1.setCellVerticalAlignment(lblNewLabel,
				HasVerticalAlignment.ALIGN_BOTTOM);
		image.addErrorHandler(new ErrorHandler() {
			public void onError(ErrorEvent event) {
				System.out.println("video error");
			}
		});
		elapsedTimer = new Timer() {
			public void run() {

				image.setUrl("video?BOTID=" + BOT_ID + "&st="
						+ System.currentTimeMillis());
				eventService.getLag(System.currentTimeMillis(), BOT_ID,
						new AsyncCallback<Long>() {

							@Override
							public void onSuccess(Long result) {
								lblLag.setText("Video Lag :" + result + " ms");
							}

							@Override
							public void onFailure(Throwable caught) {
								lblLag.setText("Network Error");
							}
						});

			}
		};
		elapsedTimer.scheduleRepeating(50);
	}
}
