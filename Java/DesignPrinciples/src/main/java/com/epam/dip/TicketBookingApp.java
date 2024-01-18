package com.epam.dip;

public class TicketBookingApp {
	public static void main(String[] args) {
		TicketBookingClient paymentType=new TicketBookingClient(new CreditCard());
		paymentType.dooPayment(3, 3500);
	}
}
