package com.epam;
import java.util.*;
public class FindingKDays
{
	String day;
	int k;
	FindingKDays(String a,int b)
	{
		this.day=a;
		this.k=b;
	}
	void DayAfterKdays()
	{
		//ArrayList<String> days=new ArrayList<>() {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
	    String[] days=new String[] {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
		List<String> l=Arrays.asList(days);
		int count=l.indexOf(day);
		k=k+count;
		if(k<7)
		{
			System.out.println(l.get(k));
		}
		else
		{
			k=k%7;
			System.out.println(l.get(k));
		}
	}
}