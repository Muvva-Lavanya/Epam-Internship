package com.epam.decorator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Widget2 extends WidgetDecorator{
	final Logger logger=LogManager.getLogger(Widget2.class);
	public Widget2(Webpage webpage) {
		super(webpage);
		// TODO Auto-generated constructor stub
	}
	@Override
	public int ranking()
	{
		logger.info("Widget2");
		return super.ranking()+20;
		
	}
}
