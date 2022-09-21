package com.wv.testing;

import java.util.Optional;

import test.UserTest;

public class TestingOptional {

	/* nice article for Optional:
	/ https://tomgregory.com/java-optional/#:~:text=Java's%20Optional%20offers%20a%20rich,if%20the%20Optional%20is%20empty
	*/
	public static void main(String[] args) {

		Integer i = null;
		Optional.ofNullable(i).ifPresentOrElse(
	            v -> System.out.println("integer is not null " + v), 
	            () -> System.out.println("integer is null")
	    );
		//------------------------------------
		//Optional in reality is a stream of size 1, so we can use all stream methods like filter, map ..etc
		UserTest userTest = new UserTest(2L, 25, "mahemail.com");
		UserTest user2 =null;
		
		Optional<UserTest> result = Optional.ofNullable(user2)
			      .filter(u -> u.getEmail() != null && u.getEmail().contains("@"));
			    
		System.out.println(result.isPresent());
	}

}
