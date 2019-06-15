package com.wsonoma.zipInfoService.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
	
	@Test
	void testMinimize3() throws FileNotFoundException {
		// incorrect input illiminate one of the sets
		ArrayList<String> minList = new ArrayList<>(Arrays.asList("996", "94785", "99874", "12345"));
		ArrayList<String> maxList = new ArrayList<>(Arrays.asList("99635", "99786", "99974", "99999"));
		
		assertEquals(rs.minimize(in.getInput(minList, maxList).get("zipRange-demo")).get(0).getMinRange(), 12345);
		assertEquals(rs.minimize(in.getInput(minList, maxList).get("zipRange-demo")).get(0).getMaxRange(), 99999);
		assertEquals(rs.minimize(in.getInput(minList, maxList).get("zipRange-demo")).size(), 1);
	}
	
	@Test
	void testMinimize4() throws FileNotFoundException {
		// incorrect input illiminate one of the sets
		ArrayList<String> minList = new ArrayList<>(Arrays.asList("996a1", "94785", "99874", "99988"));
		ArrayList<String> maxList = new ArrayList<>(Arrays.asList("99635", "99786", "99974", "99999"));
		
		assertEquals(rs.minimize(in.getInput(minList, maxList).get("zipRange-demo")).get(0).getMinRange(), 94785);
		assertEquals(rs.minimize(in.getInput(minList, maxList).get("zipRange-demo")).get(0).getMaxRange(), 99786);
		assertEquals(rs.minimize(in.getInput(minList, maxList).get("zipRange-demo")).get(1).getMinRange(), 99874);
		assertEquals(rs.minimize(in.getInput(minList, maxList).get("zipRange-demo")).get(1).getMaxRange(), 99974);
		assertEquals(rs.minimize(in.getInput(minList, maxList).get("zipRange-demo")).get(2).getMinRange(), 99988);
		assertEquals(rs.minimize(in.getInput(minList, maxList).get("zipRange-demo")).get(2).getMaxRange(), 99999);
		assertEquals(rs.minimize(in.getInput(minList, maxList).get("zipRange-demo")).size(), 3);
	}
	
	// More tests can be provided to test many other scenarios 

}
