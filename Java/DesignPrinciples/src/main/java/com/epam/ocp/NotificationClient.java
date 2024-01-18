package com.epam.ocp;

public class NotificationClient {
	private Notification notification;

	public NotificationClient(Notification notification) {
		super();
		this.notification = notification;
	}
	public void sendOTP(String medium)
	{
		notification.sendOTP(medium);
	}
}
