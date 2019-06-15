package com.wsonoma.zipInfoService.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.wsonoma.zipInfoService.data.RangeData;

public class OutputManagerFlatFileImpl implements OutputManager{

	// This Implementation will write into a json file.
	// To change the extension of the output file change the property of IOTypes.FLAT_FILE_EXTENTION
	
	static Logger logger = LogService.getInstance();
	private String outtDir = IOConfigurations.FLAT_FILE_TARGET;
	
	
	@Override
	public void generateOutput(String fileName, ArrayList<RangeData> zipRange) {
		
		IOConfigurations.props.getProperty("inputType");
		
		
		// Create json object from zipRange
		Gson gson = new Gson();
		Map<String, ArrayList<ArrayList>> json = new HashMap<>();
		ArrayList<ArrayList> zips = new ArrayList<>();
		
		for (int i = 0; i < zipRange.size(); i++) {
			ArrayList<Integer> singleRange = new ArrayList<>();
			singleRange.add(zipRange.get(i).getMinRange());
			singleRange.add(zipRange.get(i).getMaxRange());
			zips.add(singleRange);
		}
		json.put(IOConfigurations.FLAT_FILE_NAME, zips);
		
		// make sure the file have json extencion - this can be a parameter as well
		if (!fileName.contains(IOConfigurations.FLAT_FILE_EXTENTION)) {
			fileName = fileName + "." + IOConfigurations.FLAT_FILE_EXTENTION;
		}

		// write to file
		Writer writer = null;
		try {
			logger.info("Write into file " + fileName);
			writer = new FileWriter(outtDir + "\\" + fileName);
			gson.toJson(json, writer);
			writer.flush(); //flush data to file
			writer.close();
		} catch (IOException e) {
			logger.info(e);
		}
	}
}