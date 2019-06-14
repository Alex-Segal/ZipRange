package com.wsonoma.zipInfoService.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import com.wsonoma.zipInfoService.data.RangeData;
import com.wsonoma.zipInfoService.logic.RestrictShippmentZipRange;
import com.wsonoma.zipInfoService.logic.RestrictShippmentZipRangeImpl;
import com.wsonoma.zipInfoService.util.IOConfigHandler;
import com.wsonoma.zipInfoService.util.InputManager;
import com.wsonoma.zipInfoService.util.OutputManager;

public class ZipInfoServiceExecutable {

	public static void main(String[] args) throws IOException {

		// get configurations
		IOConfigHandler ioConfig = new IOConfigHandler();
		
		// get handlers for input and output types
		InputManager inputHandler = ioConfig.getInputHandler();
		OutputManager outputHandler = ioConfig.getOutputHandler();
		
		// get data from input (file)
		Map<String,ArrayList<RangeData>> input = inputHandler.getInput();
		
		// 
		RestrictShippmentZipRange zip = new RestrictShippmentZipRangeImpl();
		for (Map.Entry<String,ArrayList<RangeData>> entry : input.entrySet()) {
			// minimize the dataset (requested algorithm)
			ArrayList<RangeData> output = zip.minimize(entry.getValue());
			
			// Write to target output (file)
			outputHandler.generateOutput(entry.getKey(), output);
			
		}
	}
}