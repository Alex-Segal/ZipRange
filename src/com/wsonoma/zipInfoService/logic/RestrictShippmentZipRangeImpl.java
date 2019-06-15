package com.wsonoma.zipInfoService.logic;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.wsonoma.zipInfoService.data.RangeData;

public class RestrictShippmentZipRangeImpl implements RestrictShippmentZipRange{

	// This implementation provides minimizes the zip range into the smallest amount of ranges from a givin set
	
	@Override
	public ArrayList<RangeData> minimize(ArrayList<RangeData> input) {
		
		// sort the dataset based the min zip
		List<RangeData> sortedInput = input.stream()
				  .sorted(Comparator.comparing(RangeData::getMinRange))
				  .collect(Collectors.toList());
		
		// This value holds the current maximum zip code.
		// It will help us to know if the next pair overlap fully or not with ANY previous set
		int currMaxZip = 0;
		
		// check if the range's max is not fully existing within any other set
		ArrayList<RangeData> output = new ArrayList<>();
		if (sortedInput.size() > 0) {
			// Check for currMaxZip = 0 to make sure first element (with smallest minRange) is always get's into the set and prevent IndexOutOfBoundsException
			for (int i = 0; i < sortedInput.size(); i++) {
				if (i == 0 || (sortedInput.get(i).getMaxRange() > sortedInput.get(i-1).getMaxRange() 
								&& sortedInput.get(i).getMinRange() < sortedInput.get(i).getMaxRange()
									&& sortedInput.get(i).getMaxRange() > currMaxZip)) {
					currMaxZip = sortedInput.get(i).getMaxRange();
					output.add(sortedInput.get(i));
				}
			}
		}
		return output;
	}
}