//package com.wv.testing;
//
//import java.util.function.Consumer;
//import java.util.function.Predicate;
//import java.util.function.Supplier;
//import java.util.stream.Stream;
//
//public class TestStreams {
//
//	public static boolean isLongerThan4(String word) {
//		return word != null &&  word.length()>4;
//	}
//	
//	public static void main(String[] args) {
//		
//		Predicate<String> biggerThan4Predicate = TestStreams::isLongerThan4;
//		/*supplier is added only to use the Stream more than once 
//		*and avoid the exception: stream has already been operated upon or closed
//		*what supplier does is it create new Stream every time get() is called
//		*Supplier<T> is functional interface that can be applied to anything.
//		*
//		*   Example:
//		*   -------
//		*   Supplier<String> strSupplier = ()->"ee";
//		*   System.out.println(strSupplier.get());
//		*/
//		Supplier<Stream<String>> wordsStreamSupplier = () -> Stream.of("nano", "bingo", "mahmoud", "lola", "go","mourad");
//		Supplier<Stream<String>> big4StreamSuppier = ()->wordsStreamSupplier.get().filter(biggerThan4Predicate);
//		Supplier<Stream<String>> big5StreamSuppier = ()->wordsStreamSupplier.get().filter(s-> s.length() > 5);
//		
//		boolean allMatch = big4StreamSuppier.get().allMatch(w -> w.length()>4);
//		System.out.println(allMatch);
//		System.out.println("%%%%%%%%%%%%%%%%%%%%%");
//		System.out.println(big4StreamSuppier.get().toList());
//		System.out.println("*********************");
//		wordsStreamSupplier.get().forEach(w->System.out.println(w));
//		System.out.println("********************");
//		Consumer<String> consumerStr = s->{System.out.print(s); System.out.print(" - ");}; //accept method of consumer just takes one argument execute some code and return nothing
//		big5StreamSuppier.get().forEach(consumerStr);
//		System.out.println("%%%%%%%%%%%%%%%%%%%%%");
//		
//		big5StreamSuppier.get().map(null);
//		//------------------------------------------
//		Supplier<String> strSupplier = ()->"ee";
//		System.out.println(strSupplier.get());
//		
//		
//	}
//
//}
