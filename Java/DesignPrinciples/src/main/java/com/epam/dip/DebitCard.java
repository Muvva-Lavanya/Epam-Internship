package com.epam.dip;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class DebitCard implements CardPaymentMethod{
	final Logger logger=LogManager.getLogger(DebitCard.class);
	@Override
	public void doPayment(int noOfTickets, int amount) {
		// TODO Auto-generated method stub
		int total=noOfTickets*amount;
		logger.info("Using Debitcard : "+total);
		
	}
}
