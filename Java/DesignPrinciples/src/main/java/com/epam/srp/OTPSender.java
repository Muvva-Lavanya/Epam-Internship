package com.epam.srp;

public class OTPSender {
	private OTPClient otpClient;

	public OTPSender(OTPClient otpClient) {
		this.otpClient = otpClient;
	}
	public void sendOTP() {
		otpClient.sendOTP();
	}
	

}
