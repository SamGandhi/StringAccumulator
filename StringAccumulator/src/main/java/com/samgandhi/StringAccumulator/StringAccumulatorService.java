package com.samgandhi.StringAccumulator;

public class StringAccumulatorService {

	public static void main(String argv[]) {
		System.out.println("hello world");
		StringAccumulatorImp service = new StringAccumulatorImp();
		try {
			System.out.println(service.add("34,\n"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
}
