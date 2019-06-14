package com.wsonoma.zipInfoService.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.wsonoma.zipInfoService.data.RangeData;
import com.wsonoma.zipInfoService.util.InputManagerDemoImpl;

class RestrictShippmentZipRangeImplTest {
	
	RestrictShippmentZipRange rs;
	InputManagerDemoImpl in;

	@BeforeEach
	void setUp() throws Exception {
		this.rs = new RestrictShippmentZipRangeImpl();
		this.in = new InputManagerDemoImpl();
	}

	@Test
	void testMinimize1() throws FileNotFoundException {
		ArrayList<String> minList = new ArrayList<>(Arrays.asList("99632", "94785"));
		ArrayList<String> maxList = new ArrayList<>(Arrays.asList("99635", "99786"));
		
		assertEquals(rs.minimize(in.getInput(minList, maxList).get("zipRange-demo")).get(0).getMinRange(), 94785);
		assertEquals(rs.minimize(in.getInput(minList, maxList).get("zipRange-demo")).get(0).getMaxRange(), 99786);
		assertEquals(rs.minimize(in.getInput(minList, maxList).get("zipRange-demo")).size(), 1);
	}
	
	@Test
	void testMinimize2() throws FileNotFoundException {
		// incorrect input illiminate one of the sets
		ArrayList<String> minList = new ArrayList<>(Arrays.asList("99632", "94785a"));
		ArrayList<String> maxList = new ArrayList<>(Arrays.asList("99635", "99786"));
		
		assertEquals(rs.minimize(in.getInput(minList, maxList).get("zipRange-demo")).get(0).getMinRange(), 99632);
		assertEquals(rs.minimize(in.getInput(minList, maxList).get("zipRange-demo")).get(0).getMaxRange(), 99635);
		assertEquals(rs.minimize(in.getInput(minList, maxList).get("zipRange-demo")).size(), 1);
	}

}
