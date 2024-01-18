package com.epam;

public class Customer implements Comparable<Customer>{
	private String customerName;
	private double customerSalary;
	public Customer(String customerName, double customerSalary)
	{
		//super();
		this.customerName = customerName;
		this.customerSalary = customerSalary;
	}
	public static String toString(Customer obj)
	{
		return "CustomerName ="+obj.getCustomerName()+" "+"Customer Salary "+obj.getCustomerSalary();
	
	}
	public String getCustomerName() 
	{
		return customerName;
	}
	public double getCustomerSalary()
	{
		return customerSalary;
	}
	public int compareTo(Customer compare)
	{
		if(this.customerSalary>compare.customerSalary)
		{
			return 1;
		}
		else
		{
		return 0;
		}
	}
}