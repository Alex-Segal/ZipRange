package com.wsonoma.zipInfoService.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class IOConfigurations {
	
	private IOConfigurations(){
	}
	
	static Logger logger = LogService.getInstance();
	
	static public Properties props;
	static String inputType;
	static String outputType;
	static String FLAT_FILE_LZ;
	static String FLAT_FILE_TARGET;
	static String FLAT_FILE_EXTENTION;
	static String FLAT_FILE_NAME;
	static String FLAT_FILE_TAG;
	
	static public void loadConfigurations() {
		File configFile = new File("Configuration\\zipConfig.properties");
		try {
		    FileReader reader = new FileReader(configFile);
		    props = new Properties();
		    props.load(reader);
		    IOConfigurations.inputType = props.getProperty("inputType");
		    IOConfigurations.outputType = props.getProperty("outputType");
		    IOConfigurations.FLAT_FILE_LZ = props.getProperty("FLAT_FILE_LZ");
		    IOConfigurations.FLAT_FILE_TARGET = props.getProperty("FLAT_FILE_TARGET");
		    IOConfigurations.FLAT_FILE_EXTENTION = props.getProperty("FLAT_FILE_EXTENTION");
		    IOConfigurations.FLAT_FILE_NAME = props.getProperty("FLAT_FILE_NAME");
		    IOConfigurations.FLAT_FILE_TAG = props.getProperty("FLAT_FILE_TAG");
		    reader.close();
		} catch (FileNotFoundException ex) {
			logger.info(ex);
		} catch (IOException ex) {
			logger.info(ex);
		}
	}

}
