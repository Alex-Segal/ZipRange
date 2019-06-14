package com.wsonoma.zipInfoService.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.wsonoma.zipInfoService.data.IOTypes;
import com.wsonoma.zipInfoService.data.RangeData; 

public class InputManagerFlatFileImpl implements InputManager{

	private String inputDir = IOTypes.FLAT_FILE_LZ;
	
	@Override
	public Map<String,ArrayList<RangeData>> getInput() throws FileNotFoundException {
		
		File folder = new File(inputDir);
		ArrayList<String> files = listFilesForFolder(folder);
		return convertJson(files);
	}

	private ArrayList<String> listFilesForFolder(final File folder) {
		
		ArrayList<String> files = new ArrayList<>();
	    for (String fileEntry : folder.list()) {
	    	if (fileEntry.contains("zipRange"))
	    		files.add(fileEntry);
	    }
	    return files;
	}
	
	private Map<String,ArrayList<RangeData>> convertJson(ArrayList<String> files) throws FileNotFoundException {
		
		
		Map<String, ArrayList<RangeData>> fileMap = new HashMap<String, ArrayList<RangeData>>();

		for (String file : files) {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			ArrayList<RangeData> zipRanges = new ArrayList<>();
			try (Reader reader = new FileReader(inputDir + "\\" + file)) {
				
	            // get json element contain zip ranges
				JsonElement json = gson.fromJson(reader, JsonElement.class);
				JsonArray zips = json.getAsJsonObject().get("zipRanges").getAsJsonArray();
				
				// convert json to string array
	            Type type = new TypeToken<ArrayList<String>>(){}.getType();
	            ArrayList<String> list =  gson.fromJson(zips.get(0), type );
	            
	            for (int i = 0; i < zips.size(); i++) {
	            	ArrayList<String> zip =  gson.fromJson(zips.get(i), type );
	            	if (ZipValidator.validateZip(zip)) {
		            	RangeData zipRange = new RangeData();
		            	zipRange.setMinRange(Integer.parseInt(zip.get(0)));
		            	zipRange.setMaxRange(Integer.parseInt(zip.get(1)));
		            	zipRanges.add(zipRange);
	            	}
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
			fileMap.put(file, zipRanges);
		}
		return fileMap;
	}
}