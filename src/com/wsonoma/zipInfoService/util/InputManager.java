package com.wsonoma.zipInfoService.util;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;

import com.wsonoma.zipInfoService.data.RangeData;

public interface InputManager {
	public Map<String, ArrayList<RangeData>> getInput() throws FileNotFoundException;
}
