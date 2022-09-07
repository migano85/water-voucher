package com.wv.testing;

import java.util.Comparator;
import java.util.Objects;

public class classWithMain {

	public static void main(String[] args) {
//		String first = "first";
//		String second = "Second";

		
		Comparator<String> compExample1 = (first, second)->Integer.compare(first.length(), second.length());
		
		System.out.println(compExample1.compare("1176", "228"));
		
		Comparator<Double> compExample2 = (s, q) -> Objects.equals(q, s)?1:2;//we should return int because this is the return type of compare() the single method in the interface
		
		System.out.println(compExample2.compare(1176.2, 228.0)); //compExample2 accepts only Double
		
		Comparator<Double> compTheOldWay = new Comparator<Double>() {
			//before java 8 there was inline implementation for interfaces, not only functional interfaces ut even the one which have more than one interface like List
			
			@Override
			public int compare(Double s, Double q) {
				return Objects.equals(q, s)?1:2;
			}
		};
		
		System.out.println(compTheOldWay.compare(1176.2, 228.0)); //compTheOldWay accepts only Double
	}

}
