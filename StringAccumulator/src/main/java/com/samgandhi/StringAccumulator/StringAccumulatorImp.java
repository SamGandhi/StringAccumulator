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

	@Override
	public int add(String numbers) throws Exception {
		String negativeList = "";
		List<String> delimiters = getdelimiters(numbers);
		if (numbers.substring(0, 2).equals("//")) {
			numbers = numbers.substring(numbers.indexOf("\n") + 1);
		}
		for (int i = 0; i < delimiters.size(); i++) {
			numbers = numbers.replaceAll(Pattern.quote(delimiters.get(i)), StringAccumulator.DEFAULT_DELIMITER);
		}
		negativeArray = new ArrayList<Integer>();
		List<Integer> list = parseIntegerNumber(numbers, StringAccumulator.DEFAULT_DELIMITER);
		Optional<Integer> sum = list.stream().filter(s -> s.intValue() <= StringAccumulator.MAX_ADD_NUMBER)
				.reduce(Integer::sum);
		if (negativeArray.size() > 0) {
			for (int negative : negativeArray) {
				negativeList = negativeList.equals("") ? negativeList : negativeList + ", ";
				negativeList += negative;
			}
			throw new Exception("negatives not allowed : " + negativeList);
		} else if (list.size() <= 1) {
			throw new Exception("Invalid Input..");
		} else {
			return sum.get();
		}
	}

	@Override
	public ArrayList<String> getdelimiters(String numbers) {
		ArrayList<String> delimitorPatterns = new ArrayList<String>();
		if (numbers.substring(0, 2).equals("//")) {
			numbers = numbers.substring(2);
			delimitorPatterns = (ArrayList<String>) Arrays
					.asList(numbers.substring(0, numbers.indexOf("\n")).split(Pattern.quote("|"))).stream().distinct()
					.map(Object::toString).collect(Collectors.toList());
			return delimitorPatterns;
		}

		if (!delimitorPatterns.contains(",")) {
			delimitorPatterns.add(",");
		}
		return delimitorPatterns;
	}

	@Override
	public List<Integer> parseIntegerNumber(String number, String delimator) throws Exception {
		try {
			int[] numericList = Stream.of(number.split(delimator)).mapToInt(Integer::parseInt)
					.peek(n -> getNegativeNumbers(n)).peek(s -> {
						if ("".equals(s))
							throw new IllegalArgumentException(String.valueOf(s));
					}).toArray();
			return Arrays.stream(numericList).boxed().collect(Collectors.toList());
		} catch (Exception e) {
			System.out.println("Exception thrown :" + e.getLocalizedMessage());
		}
		return null;
	}

	private Integer getNegativeNumbers(Integer value) {
		if (value < 0) {
			negativeArray.add(value);
			return 0;
		} else {
			return value;
		}
	}

}
