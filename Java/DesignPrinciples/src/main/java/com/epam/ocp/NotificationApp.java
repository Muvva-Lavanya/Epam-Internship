package com.epam.ocp;

public class NotificationApp {
	public static void main(String[] args) {
		NotificationClient notificationClient = new NotificationClient(new Email());
		notificationClient.sendOTP("email");
	}
}
