package com.wsonoma.zipInfoService.util;

import java.util.ArrayList;

public class ZipValidator {

	static boolean validateZip(ArrayList<String> zip) {
		String regex = "^[0-9]{5}$";
		return zip.get(0).matches(regex) && zip.get(1).matches(regex);
	}
}
