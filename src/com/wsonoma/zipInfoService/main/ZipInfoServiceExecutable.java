package com.wsonoma.zipInfoService.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.log4j.Logger;

import com.wsonoma.zipInfoService.data.RangeData;
import com.wsonoma.zipInfoService.logic.RestrictShippmentZipRange;
import com.wsonoma.zipInfoService.logic.RestrictShippmentZipRangeImpl;
import com.wsonoma.zipInfoService.util.IOConfigFactory;
import com.wsonoma.zipInfoService.util.InputManager;
import com.wsonoma.zipInfoService.util.LogService;
import com.wsonoma.zipInfoService.util.OutputManager;

public class ZipInfoServiceExecutable {
	
	static Logger logger = LogService.getInstance();

	public static void main(String[] args) throws IOException {

		// get configurations
		logger.info("Load configurations");
		IOConfigFactory ioConfig = new IOConfigFactory();
		
		// get handlers for input and output types
		logger.info("Get inputHandler");
		InputManager inputHandler = ioConfig.getInputHandler();
		logger.info("Get outputHandler");
		OutputManager outputHandler = ioConfig.getOutputHandler();
		
		// get data from input (file)
		logger.info("Get data from input file");
		Map<String,ArrayList<RangeData>> input = inputHandler.getInput();
		
		RestrictShippmentZipRange zip = new RestrictShippmentZipRangeImpl();
		
		// for each input file run minimize process
		for (Map.Entry<String,ArrayList<RangeData>> entry : input.entrySet()) {
			// minimize the dataset (requested algorithm)
			logger.info("Prosses input: " + entry.getKey());
			ArrayList<RangeData> output = zip.minimize(entry.getValue());
			
			// Write to target output (file)
			logger.info("Presist: " + entry.getKey());
			outputHandler.generateOutput(entry.getKey(), output);
			
		}
	}
}