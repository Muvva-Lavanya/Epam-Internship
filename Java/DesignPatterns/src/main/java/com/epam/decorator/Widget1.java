package com.epam.decorator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Widget1 extends WidgetDecorator{
	final Logger logger=LogManager.getLogger(Widget1.class);
	public Widget1(Webpage webpage) {
		super(webpage);
		// TODO Auto-generated constructor stub
	}
	@Override
	public int ranking()
	{
		logger.info("Widget1");
		return super.ranking()+15;
		
	}

}
