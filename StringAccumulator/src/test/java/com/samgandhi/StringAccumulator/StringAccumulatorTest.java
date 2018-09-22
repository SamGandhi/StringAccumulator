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
		return (Collection) Arrays.asList(
				new Object[][] { { "", 0 }, { "1", 1 }, { "1,2", 3 }, { "1,2,3,4,5,6,7,89,90,26,78,57,89", 457 },
					{ "1\n2,3", 6 }, { "1,\n", 0 }, { "//;\n1;2;", 3 }, { "2,-1001", 2 }, { "//***\n1***2***3", 6 },
					{ "//*|%\n1*2%3", 6 }, { "//**|,|^\n1\n2\n", 3 }, { "//***|%%%|^^^\n1***2%%%3^^^4", 10 } });
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
