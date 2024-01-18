package com.epam;

public class GenericUtility{
	public static <T extends Comparable<T>> T receiveLeastValue( T object1 , T object2) 
	{
		if(object1.compareTo(object2)==1)
			return object2;
		else
			return object1;
	}
}
