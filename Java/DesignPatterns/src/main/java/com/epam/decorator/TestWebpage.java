package com.epam.decorator;

public class TestWebpage {
	public static void main(String[] args) {
		Webpage webpage=new DesktopVersion();
		Webpage widget1=new Widget1(new DesktopVersion());
		Webpage widget2=new Widget2(new MobileVersion());
		System.out.println(webpage.ranking());
		System.out.println(widget1.ranking());
		System.out.println(widget2.ranking());
	}
}
