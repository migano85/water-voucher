package com.wv.testing;

import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class TestStreams {

	public static boolean isLongerThan4(String word) {
		return word != null &&  word.length()>4;
	}
	
	public static void main(String[] args) {
		
		Predicate<String> biggerThan4 = TestStreams::isLongerThan4;
		/*supplier is added only to use the Stream more than once 
		*and avoid the exception: stream has already been operated upon or closed
		*what supplier does is it create new Stream every time get() is called
		*Supplier<T> is functional interface that can be applied to anything.
		*
		*   Example:
		*   -------
		*   Supplier<String> strSupplier = ()->"ee";
		*   System.out.println(strSupplier.get());
		*/
		Supplier<Stream<String>> wordsStreamSupplier = () -> Stream.of("nano", "bingo", "mahmoud", "lola", "go");
		Supplier<Stream<String>> big4StreamSuppier = ()->wordsStreamSupplier.get().filter(biggerThan4);
		
		boolean allMatch = big4StreamSuppier.get().allMatch(w -> w.length()>4);
		System.out.println(allMatch);
		System.out.println(big4StreamSuppier.get().toList());
		wordsStreamSupplier.get().forEach(w->System.out.println(w));
		
		//------------------------------------------
		Supplier<String> strSupplier = ()->"ee";
		System.out.println(strSupplier.get());
		
		
	}

}
