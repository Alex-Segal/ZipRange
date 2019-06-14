package com.wsonoma.zipInfoService.util;

import java.io.IOException;
import java.util.ArrayList;

import com.wsonoma.zipInfoService.data.RangeData;

public interface OutputManager {
	
	public void generateOutput(String fileName, ArrayList<RangeData> zipRange) throws IOException;

}