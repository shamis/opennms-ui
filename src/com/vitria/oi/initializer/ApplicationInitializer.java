package com.vitria.oi.initializer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.vitria.oi.log.analyzer.CommonsFileTail;
import com.vitria.oi.monitoring.PropertyReader;
import com.vitria.oi.monitoring.events.Events;

public class ApplicationInitializer implements ServletContextListener{

	CommonsFileTail tail;

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("De - initializing");
//		tail.cleanup();
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("*******************************************************Initializing");
		//Events.findAndSendEvents();
//		tail = new CommonsFileTail();
//		tail.checkJBossServLog(PropertyReader.getString("logFiles"));
	}

}
