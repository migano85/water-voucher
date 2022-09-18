package com.wv.testing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.concurrent.Callable;

import test.User;

public class classWithMain{
	
	public static void main(String[] args) {
//		String first = "first";
//		String second = "Second";

		Comparator<User> compUser = (u1,u2)->u1.getAge().compareTo(u2.getAge());
		ArrayList<User> list = new ArrayList<>(); list.add(new User(9L,78)); list.add(new User(4L,55)); list.add(new User(3L,44)); list.add(new User(2L,22));
		System.out.println(list); list.sort(compUser); System.out.println(list);
		
//		Comparator<String> compExample1 = (first, second)->Integer.compare(first.length(), second.length());
//		
//		System.out.println(compExample1.compare("1176", "228"));
//		
//		Comparator<Double> compExample2 = (s, q) -> Objects.equals(q, s)?1:2;//we should return int because this is the return type of compare() the single method in the interface
//		
//		System.out.println(compExample2.compare(1176.2, 228.0)); //compExample2 accepts only Double
//		
//		Comparator<Double> compTheOldWay = new Comparator<Double>() {
//			//before java 8 there was inline implementation for interfaces, not only functional interfaces ut even the one which have more than one interface like List
//			
//			@Override
//			public int compare(Double s, Double q) {
//				return Objects.equals(q, s)?1:2;
//			}
//		};
//		
//		Callable<Integer> cc = ()->{
//			System.out.println("for this interface, you can return anything");
//			System.out.println("lamda can have more than one line of code");
//			return null;
//		};
		/*Difference Between Callable and Runnable in Java, check this
		 * https://www.geeksforgeeks.org/difference-between-callable-and-runnable-in-java/
		 */
				
//		System.out.println(compTheOldWay.compare(1176.2, 228.0)); //compTheOldWay accepts only Double
//		String strs[]= {"coco", "nono", "bing bong", "ringo"};
//		
//		Arrays.sort(strs, compExample1);
//		Arrays.sort(strs, (a, b)->Integer.compare(a.length(), b.length()));
//		Arrays.sort(strs, (a,b)->a.compareToIgnoreCase(b));
//		Arrays.sort(strs, String::compareToIgnoreCase);
		
	}

}
