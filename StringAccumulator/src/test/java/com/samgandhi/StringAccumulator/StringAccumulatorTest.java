package com.samgandhi.StringAccumulator;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import junit.framework.TestCase;

import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Enclosed.class)
public class StringAccumulatorTest extends TestCase {
	
	@RunWith(Parameterized.class)
	public static class ComponentParamTest{
	
		private String str;
		private int expectedResult;
		
		public ComponentParamTest(String str, int expectedResult) {
			this.str = str;
			this.expectedResult = expectedResult;
		}
		@Parameterized.Parameters
		public static Collection inputStrings() {
			return (Collection) Arrays.asList(
					new Object[][] { { "", 0 }, { "1", 1 }, { "1,2", 3 }, { "1,2,3,4,5,6,7,89,90,26,78,57,89", 457 },
						{ "1\n2,3", 6 }, { "1,\n", 0 }, { "//;\n1;2;", 3 }, { "//***\n1***2***3", 6 },
						{ "//*|%\n1*2%3", 6 }, { "//**|,|^\n1\n2\n", 3 }, { "//***|%%%|^^^\n1***2%%%3^^^4", 10 } });
		}
		
		@Test
		public void testStrinAccumulatorChecker() {
			System.out.println("Input string is : " + str);
			try {
				assertEquals(expectedResult, new StringAccumulatorImp().add(str));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	
	public static class ComponentSingleTests{
		@Test
		public void testExceptionMessage() {
			try{
				new StringAccumulatorImp().add("2,-1001");
			}catch (Exception e) {
				assertEquals("negatives not allowed : -1001", e.getMessage());
			}
		}
		
		@Test
		public void test2ExceptionMessage() {
			try{
				new StringAccumulatorImp().add("2,-32,-25");
			}catch (Exception e) {
				assertEquals("negatives not allowed : -32, -25", e.getMessage());
			}
		}
	}
}
