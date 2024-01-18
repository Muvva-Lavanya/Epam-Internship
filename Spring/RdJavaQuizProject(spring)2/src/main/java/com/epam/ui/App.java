package com.epam.ui;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.epam.Configurator;

public class App {
	public static void main(String[] args) {
		ApplicationContext context=new AnnotationConfigApplicationContext(Configurator.class);
		Main main=context.getBean(Main.class);
		//Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
		main.startApplication();
    	
	}
}
