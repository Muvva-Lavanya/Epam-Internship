package com.epam;


public class FindingDuplicates
{
	String s1;
	FindingDuplicates(String s1)
	{
		this.s1=s1;
	}
	public void duplicate()
	{
	String s2=s1.toLowerCase();
	char[] ch=s2.toCharArray();
	
	System.out.println("Duplicate Characters are:");
	for (int i = 0; i < s2.length(); i++) 
	{
         for (int j = i + 1; j < s2.length(); j++) 
		 {
            if (ch[i] == ch[j] && ch[i]!=32)
				{
					System.out.print(ch[j] + " ");
					break;
				}
           
		}
	}
	
	
	
}
}