package com.epam;

import java.util.Comparator;

public class NumberComparator implements Comparator<Integer>
{
	
	public int compare(Integer n1,Integer n2)
	{
		if(n1<n2)
		{
			return 1;
		}
		return -1;
	}
}