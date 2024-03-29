package com.wsonoma.zipInfoService.util;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.wsonoma.zipInfoService.data.RangeData;

public class InputManagerDemoImpl implements InputManager{

	// This method will generate fake data
	
	static Logger logger = LogService.getInstance();

	@Override
	public Map<String, ArrayList<RangeData>> getInput() {
		// create some fake data!
		ArrayList<String> minList = new ArrayList<>(Arrays.asList("99632", "94785", "36541", "89549a"));
		ArrayList<String> maxList = new ArrayList<>(Arrays.asList("99635", "99785", "36551", "89541"));
		
		return renderData(minList, maxList);
	}
	
	// This method will accept premade datasets in array forms
	public Map<String, ArrayList<RangeData>> getInput(ArrayList<String> minList, ArrayList<String> maxList) throws FileNotFoundException {

		return renderData(minList, maxList);
	}
	
	// This method will rander demo datasets into proper input
	private Map<String, ArrayList<RangeData>> renderData(ArrayList<String> minList, ArrayList<String> maxList) {
		
		Map<String, ArrayList<RangeData>> inputDataMap = new HashMap<String, ArrayList<RangeData>>();
		ArrayList<RangeData> inputData = new ArrayList<RangeData>();
		for (int i = 0; i<minList.size(); i++) {
			RangeData rd = new RangeData();
			ArrayList<String> zip = new ArrayList<String>();
			zip.add(minList.get(i));
			zip.add(maxList.get(i));
			if (ZipValidator.validateZip(zip)){
				rd.setMinRange(Integer.parseInt(minList.get(i)));
				rd.setMaxRange(Integer.parseInt(maxList.get(i)));
				inputData.add(rd);
			}
		}
		inputDataMap.put("zipRange-demo", inputData);
		logger.info("Generated data named: zipRange-demo with " + inputData.size() + " range points");
		return inputDataMap;
	}
	
	

}
