package com.epam.decorator;

public class WidgetDecorator implements Webpage{
	public Webpage webpage;
	
	public WidgetDecorator(Webpage webpage) {
		super();
		this.webpage = webpage;
	}

	public int ranking() {
		// TODO Auto-generated method stub
		return webpage.ranking();
	}

}
