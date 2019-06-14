package com.wsonoma.zipInfoService.util;

import java.io.*;
import java.util.Properties;

import com.wsonoma.zipInfoService.data.IOTypes;

public class IOConfigHandler {
	
	Properties props;
	Properties configProp = new Properties();
	public IOConfigHandler() throws IOException{
		File configFile = new File("Configuration\\zipConfig.properties");
//		File configFile = new File("ZipInfoFilter\\Configuration\\zipConfig.properties"); // for IntelliJ proj set up folder above and ZipInfoFilter is a module. CurrDir is above ZipInfoFilter!
		try {
		    FileReader reader = new FileReader(configFile);
		    props = new Properties();
		    props.load(reader);
		    reader.close();
		} catch (FileNotFoundException ex) {
		    // file does not exist
			throw new FileNotFoundException();
		} catch (IOException ex) {
		    // I/O error
			throw new IOException();
		}
	}
	
	public InputManager getInputHandler () {
		String inputType = props.getProperty("inputType");		
		switch (inputType) {
			case IOTypes.FLAT_FILE:
				return new InputManagerFlatFileImpl();
			case IOTypes.DATABASE:
				return null;
			case IOTypes.DEMO:
				return new InputManagerDemoImpl();
		}
		return null;
	}
	
	public OutputManager getOutputHandler () {
		String inputType = props.getProperty("outputType");	
		switch (inputType) {
		case IOTypes.FLAT_FILE:
			return new OutputManagerFlatFileImpl();
		case IOTypes.DATABASE:
			return null;
		}
		return null;
	}
	


}