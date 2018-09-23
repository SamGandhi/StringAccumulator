package com.samgandhi.StringAccumulator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StringAccumulatorImp implements StringAccumulator {

	private ArrayList<Integer> negativeArray = null;

	/**
	 * This methods add all positive number present in string
	 * @param strNumbers
	 * @return sum 
	 * 
	 */
	@Override
	public int add(String strNumbers) throws Exception {
		String negativeList = "";
		List<Integer> list = new ArrayList<Integer>();
		List<String> delimiters = getdelimiters(strNumbers);
		if (strNumbers.contains("//")) {
			if (strNumbers.substring(0, 2).equals("//")) {
				strNumbers = strNumbers.substring(strNumbers.indexOf("\n") + 1);
			}
		}
		for (int i = 0; i < delimiters.size(); i++) {
			strNumbers = strNumbers.replaceAll(Pattern.quote(delimiters.get(i)), StringAccumulator.DEFAULT_DELIMITER);
		}
		negativeArray = new ArrayList<Integer>();
		list = parseIntegerNumber(strNumbers, StringAccumulator.DEFAULT_DELIMITER);
		Optional<Integer> sum = list.stream().filter(s -> s.intValue() <= StringAccumulator.MAX_ADD_NUMBER)
				.reduce(Integer::sum);
		if (negativeArray.size() > 0) {
			for (int negative : negativeArray) {
				negativeList = negativeList.equals("") ? negativeList : negativeList + ", ";
				negativeList += negative;
			}
			throw new Exception("negatives not allowed : " + negativeList);
		}  else {
			return sum.get();
		}
	}

	/**
	 * Parse first string is starts with // and identify delimiters in scope
	 * each delimiter should be separated by | symbol
	 * 
	 * @param strDelimiter
	 * @return listDelimiterPatterns	ArrayList<String>
	 * 
	 */
	@Override
	public ArrayList<String> getdelimiters(String strDelimiter) {
		ArrayList<String> listDelimiterPatterns = new ArrayList<String>();
		if (strDelimiter.contains("//")) {
			if (strDelimiter.substring(0, 2).equals("//")) {
				strDelimiter = strDelimiter.substring(2);
				listDelimiterPatterns = (ArrayList<String>) Arrays
						.asList(strDelimiter.substring(0, strDelimiter.indexOf("\n")).split(Pattern.quote("|"))).stream()
						.distinct().map(Object::toString).collect(Collectors.toList());
				return listDelimiterPatterns;
			}
		}

		if (!listDelimiterPatterns.contains(",")) {
			listDelimiterPatterns.add(",");
		}
		return listDelimiterPatterns;
	}

	/**
	 * Parse integer numbers in a string separated by a delimiter
	 * @param number delimiters	
	 * @Return List of integers
	 * @throws IllegalArgumentException - substring failed to parse as Integer
	 * 
	 */
	@Override
	public List<Integer> parseIntegerNumber(String number, String delimiter) throws Exception {
		List<Integer> aList = new ArrayList<Integer>();
		if ("".trim().equals(number)) {
			aList.add(0);
		} else {
			try {
				if (number.contains("\n\n")) {
					throw new Exception("Incorrect syntax");
				}
				int[] numericList = Stream.of(number.split(delimiter)).mapToInt(Integer::parseInt)
						.peek(n -> getNegativeNumbers(n))
						.peek(s -> {
								if ("".equals(s))
									throw new IllegalArgumentException(String.valueOf(s));
							}).
						toArray();
				aList = Arrays.stream(numericList).boxed().collect(Collectors.toList());
				return aList;
			} catch (Exception e) {
				System.out.println("Exception thrown :" + e.getMessage());
				aList.removeAll(aList);
				negativeArray.removeAll(negativeArray);
				aList.add(0);
			}
		}
		return aList;
	}

	/**
	 * This method adds negative numbers in array
	 * @param value	
	 * @throws IllegalArgumentException - substring failed to parse as Integer
	 * 
	 */
	private void getNegativeNumbers(int value) {
		if (value < 0) {
			negativeArray.add(value);
		}
	}

}
