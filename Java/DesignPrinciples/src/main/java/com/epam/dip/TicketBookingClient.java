package com.epam.dip;

public class TicketBookingClient {
	private CardPaymentMethod cardType;

	public TicketBookingClient(CardPaymentMethod cardType) {
		super();
		this.cardType = cardType;
	}
	public void dooPayment(int noOfTickets, int amount)
	{
		cardType.doPayment(noOfTickets, amount);
	}
	
}
