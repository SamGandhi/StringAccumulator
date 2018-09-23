package com.samgandhi.StringAccumulator;

import java.util.ArrayList;
import java.util.List;

public interface StringAccumulator {

	public static String DEFAULT_DELIMITER = "\n";
	public static int MAX_ADD_NUMBER = 1000;

	public int add(String numbers) throws Exception;

	public ArrayList<String> getdelimiters(String numbers);

	public List<Integer> parseIntegerNumber(String number, String delimator) throws Exception;
}
