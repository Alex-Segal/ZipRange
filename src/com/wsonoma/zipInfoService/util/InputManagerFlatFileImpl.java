package com.wsonoma.zipInfoService.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.wsonoma.zipInfoService.data.RangeData; 

public class InputManagerFlatFileImpl implements InputManager{
	
	static Logger logger = LogService.getInstance();

	// This InputManager will get data from a flat file. The current design works with json files and with file convention dictates the file to contain the string "zipRange"

	private String inputDir = IOConfigurations.FLAT_FILE_LZ;
	
	@Override
	public Map<String,ArrayList<RangeData>> getInput() {
		
		File folder = new File(inputDir);
		ArrayList<String> files = listFilesForFolder(folder);
		return convertJson(files);
	}

	private ArrayList<String> listFilesForFolder(final File folder) {
		
		ArrayList<String> files = new ArrayList<>();
	    for (String fileEntry : folder.list()) {
	    	if (fileEntry.contains(IOConfigurations.FLAT_FILE_NAME))
	    		files.add(fileEntry);
	    }
	    return files;
	}
	
	private Map<String,ArrayList<RangeData>> convertJson(ArrayList<String> files) {
		
		
		Map<String, ArrayList<RangeData>> fileMap = new HashMap<String, ArrayList<RangeData>>();

		for (String file : files) {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			ArrayList<RangeData> zipRanges = new ArrayList<>();
			try {
				logger.info("reading file: " + file);
				Reader reader = new FileReader(inputDir + "\\" + file);
	            // Get json element contain zip ranges
				JsonElement json = gson.fromJson(reader, JsonElement.class);
				JsonArray zips = json.getAsJsonObject().get(IOConfigurations.FLAT_FILE_TAG).getAsJsonArray();
				logger.info("File has " + zips.size() + " zip ranges");
				
				// Populate zipRanges object with the given input (including zip validation).
	            Type type = new TypeToken<ArrayList<String>>(){}.getType();	            
	            for (int i = 0; i < zips.size(); i++) {
	            	ArrayList<String> zip =  gson.fromJson(zips.get(i), type );
	            	if (ZipValidator.validateZip(zip)) {
		            	RangeData zipRange = new RangeData();
		            	zipRange.setMinRange(Integer.parseInt(zip.get(0)));
		            	zipRange.setMaxRange(Integer.parseInt(zip.get(1)));
		            	zipRanges.add(zipRange);
	            	}else {
	            		logger.info("Not valid zip: " + zip + " in file " + file);
	            	}
	            }
	        } catch (FileNotFoundException e) {
	            logger.info(e);
	        }
			fileMap.put(file, zipRanges);
			logger.info("Added file: " + file);
		}
		return fileMap;
	}
}