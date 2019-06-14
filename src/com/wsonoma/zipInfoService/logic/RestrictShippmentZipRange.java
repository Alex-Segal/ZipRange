package com.wsonoma.zipInfoService.logic;

import java.util.ArrayList;

import com.wsonoma.zipInfoService.data.RangeData;

public interface RestrictShippmentZipRange {
	
	public ArrayList<RangeData> minimize(ArrayList<RangeData> input);

}