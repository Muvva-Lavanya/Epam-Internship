package com.epam.isp;

public class UPIService {
	public static void main(String[] args) {
		UPIPayments upiPayment=new AbcUPI();
		upiPayment.payMoney();
		upiPayment.getScratchCard();
	}
	
}
