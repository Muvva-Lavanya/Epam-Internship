package com.epam.isp;

public interface UPIPayments {
	public void payMoney();
	public void getScratchCard();
	public default void getCashBackAsCreditBalance() {
		
	}
}
