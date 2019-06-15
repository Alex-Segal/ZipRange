package com.wsonoma.zipInfoService.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LogService {
	
	private static Logger logger;

	private LogService() {
	}
	
	// This will retrive a logger object using a Singleton pattern
	
	public static synchronized Logger getInstance(){
	    if(logger == null) {
	    	logger = Logger.getLogger(LogService.class);
	    	SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy_hh.mm.ss");
	        System.setProperty("current.date.time", dateFormat.format(new Date()));
	    	PropertyConfigurator.configure("log4j.properties");
	    }
	    return logger;
	}
	


}
