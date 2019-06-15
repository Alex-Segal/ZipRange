package com.wsonoma.zipInfoService.util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ZipValidatorTest {
	ArrayList<String> input = new ArrayList<>();

	@BeforeEach
	void setUp() throws Exception {
		input.clear();
	}

	@Test
	void testValidateZip1() {
		input.add("12345");
		input.add("12346");		
		assertEquals(ZipValidator.validateZip(input), true);
	}
	
	// here only correct zip values are validated. No logic about the min needs to be lower than max
	void testValidateZip2() {
		input.add("12345");
		input.add("11111");		
		assertEquals(ZipValidator.validateZip(input), true);
	}
	
	void testValidateZip3() {
		input.add("1T34a");
		input.add("11111");		
		assertEquals(ZipValidator.validateZip(input), false);
	}

	void testValidateZip4() {
		input.add("12345");
		input.add("11t11");		
		assertEquals(ZipValidator.validateZip(input), false);
	}
	
	void testValidateZip5() {
		input.add("1234111");
		input.add("11111");		
		assertEquals(ZipValidator.validateZip(input), false);
	}

	void testValidateZip6() {
		input.add("12345");
		input.add("11");		
		assertEquals(ZipValidator.validateZip(input), false);
	}
}
