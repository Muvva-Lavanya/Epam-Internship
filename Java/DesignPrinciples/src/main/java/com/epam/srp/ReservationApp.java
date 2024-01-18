package com.epam.srp;

public class ReservationApp {
	public static void main(String[] args) {
		InsuranceInfoGetter getter = new InsuranceInfoGetter(new PersonAccidentInsuranceClient());
		getter.getInsuranceInfo(1234);
		
		OTPSender sender=new OTPSender(new EmailOTPClient());
		sender.sendOTP();
	}
}
