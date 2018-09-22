package com.samgandhi.StringAccumulator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import junit.framework.TestCase;

import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class StringAccumulatorTest extends TestCase {

	private String str;
	private int expectedResult;
	private StringAccumulatorImp service;

	@Before
	public void initialize() {
		service = new StringAccumulatorImp();
	}

	public StringAccumulatorTest(String str, int expectedResult) {
		this.str = str;
		this.expectedResult = expectedResult;
	}

	@Parameterized.Parameters
	public static Collection inputStrings() {
		return (Collection) Arrays.asList(new Object[][] { { "//**|,|^\n2**1001,23\n34\n", 59 },
			{ "//**|,|^\n2**1000,23\n34\n", 1059 }, { "//**|,|^\n23\n34\n", 57 },
			{ "//**|,|^\n2**-1001,\n-23\n-34\n", 2 }, { "//**|,|^\n23,\n", 23 }, { "//**|,|^\n23,23", 46 }, { "//**|,|^\n23.89,23", 46 } });
	}

	@Test
	public void testStrinAccumulatorChecker() {
		System.out.println("Input string is : " + str);
		try {
			assertEquals(expectedResult, service.add(str));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
