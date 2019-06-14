package com.wsonoma.zipInfoService.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.wsonoma.zipInfoService.data.IOTypes;
import com.wsonoma.zipInfoService.data.RangeData;

public class OutputManagerFlatFileImpl implements OutputManager{

	private String outtDir = IOTypes.FLAT_FILE_TARGET;
	
	@Override
	public void generateOutput(String fileName, ArrayList<RangeData> zipRange) throws IOException {
		
		// create json object from zipRange
		Gson gson = new Gson();
		Map<String, ArrayList<ArrayList>> json = new HashMap<>();
		ArrayList<ArrayList> zips = new ArrayList<>();
		
		for (int i = 0; i < zipRange.size(); i++) {
			ArrayList singleRange = new ArrayList();
			singleRange.add(zipRange.get(i).getMinRange());
			singleRange.add(zipRange.get(i).getMaxRange());
			zips.add(singleRange);
		}
		json.put("zipRange", zips);
		
		// make sure the file have json extencion - this can be a parameter as well
		if (!fileName.contains(IOTypes.FLAT_FILE_EXTENTION)) {
			fileName = fileName + "." + IOTypes.FLAT_FILE_EXTENTION;
		}

		// write to file
		Writer writer = new FileWriter(outtDir + "\\" + fileName);
		gson.toJson(json, writer);
		writer.flush(); //flush data to file
		writer.close();
	}
}