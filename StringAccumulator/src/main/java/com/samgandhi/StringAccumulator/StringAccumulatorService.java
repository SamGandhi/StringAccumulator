package com.samgandhi.StringAccumulator;

public class StringAccumulatorService {

	public static void main(String argv[]) {
		System.out.println("Welcome to String Accumulator Service..");
		StringAccumulatorImp service = new StringAccumulatorImp();
		try {
			System.out.println(service.add("1,2,3,4,5,6,7,89,90,26,78,57,89"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
}
