package com.wv.testing;

import java.util.stream.Stream;

public class TestStreams2 {

	public static void main(String[] args) {
		//*************RULE of thumb***********
		//intermediate stream operations are lazy, will not work until you provide a terminate method
		//check https://www.baeldung.com/java-streams-peek-api for more informatu
		//**************************
		//peek()‘s Javadoc page says: “This method exists mainly to support debugging, where you want to see the elements as they flow past a certain point in a pipeline“. 
		//
		//On top of that, peek() can be useful in another scenario: when we want to alter the inner state of an element. For example, let's say we want to convert all user's name to lowercase before printing them:
		//
		//Stream<User> userStream = Stream.of(new User("Alice"), new User("Bob"), new User("Chuck"));
		//userStream.peek(u -> u.setName(u.getName().toLowerCase()))
		//  .forEach(System.out::println);
		//Alternatively, we could have used map(), but peek() is more convenient since we don't want to replace the element.
		//
		//**************************************************
//		Stream.iterate(1, n -> n + 2)
//		.peek(x -> System.out.println("peeking " + x))  it will not modify the data of the stream like map
//        .limit(5)
//        .peek(x -> System.out.println("peeking again " + x)) peek creates new stream copy of the original, that's why it will not change data unless they are reference Object, like the above example
//        .forEach(x -> System.out.println(x));
		
//		Stream.iterate("mahmoud", n->"mourad")
//		.peek(n->System.out.println(n.toUpperCase()))//peek does not transform data in stream
//		.limit(3)
//		.forEach(System.out::println);//this print lower case because peek did not transform data
		
//		Stream.iterate("mahmoud", n->"mourad")
//		.map(String::toUpperCase())//map transform data in stream, here we use the class to calls instance methods (toUpper not static method)
//		.limit(4)
//		.forEach(System.out::println);//this will print upper case
//		String str = "gigo"; str.toUpperCase();
		//--------------------------------------------
//		Stream.of(3,6,7,9)
//		.map(Integer::doubleValue)//map transform data in stream, here we use the class to calls instance methods (doubleValue not static method)
//		.limit(4)
//		.map(Double::longValue)//because first map converted integer to double, now we have to call methods of double instance
//		.forEach(System.out::println);//this will print upper case
		
//		Integer x = 5; x.doubleValue();
		
		//*************************************************************
//		Stream.iterate(new int[]{0, 1}, n -> new int[]{n[1], n[0] + n[1]})
//		.limit(10)
//		//.map(n -> n[0]) //transform array element value to value
//		.forEach(x -> {System.out.print(x[0]); System.out.print(" - "); System.out.println(x[1]);});
		//*************************************************************
		
//		Stream.iterate(new int[]{0, 1}, n -> new int[]{n[1], n[0] + n[1]})
//		.limit(10)
//		.map(n -> n[0]) //transform array element value to value
//		.forEach(System.out::println); //forEach takes a consumer then it execute its accept() method which we give implementation as println
		
		//this is will do the same as above but with 2 steps
//		Stream<Integer> s = Stream.iterate(new int[]{0, 1, 70}, n -> new int[]{n[1], n[0] + n[1]})
//		.limit(10)
//		.map(n -> n[0]);
//		
//		for(Integer n : s.toList()) {
//			System.out.println(n);
//		}
		
		//________________________________________________________
		double sum = Stream.iterate(new int[]{0, 1}, n -> new int[]{n[1], n[0] + n[1]})
		.limit(10)
		.map(n -> n[0]) //transform array element value to value, transform the result from Stream<int[]> to Stream<Integer>
		.mapToDouble(n->n)
		.sum();
		
		System.out.println(sum);
		
		//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
		// The stream.iterate was enhanced in Java 9. It supports a predicate (condition) as second argument, and the stream.iterate will stop if the predicate is false.
		Stream.iterate(1, n -> n < 10 , n -> n * 2)
		.limit(3)
        .forEach(x -> System.out.println(x));
		//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	}

}
