package com.wsonoma.zipInfoService.logic;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.wsonoma.zipInfoService.data.RangeData;

public class RestrictShippmentZipRangeImpl implements RestrictShippmentZipRange{

	@Override
	public ArrayList<RangeData> minimize(ArrayList<RangeData> input) {
		
		// sort the dataset based the min zip
		List<RangeData> sortedInput = input.stream()
				  .sorted(Comparator.comparing(RangeData::getMinRange))
				  .collect(Collectors.toList());
		
		// this value holds the current maximum zip code.
		// This will help us to know if the next pair overlap fully or not with any previous set
		int currMaxZip = 0;
		
		// check if the max zip is more than the previouse one (no full overlap)
		// 
		ArrayList<RangeData> output = new ArrayList<>();
		if (sortedInput.size() > 0) {
			for (int i = 0; i < sortedInput.size(); i++) {
				// First element with the lowest minZip always get's in.
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