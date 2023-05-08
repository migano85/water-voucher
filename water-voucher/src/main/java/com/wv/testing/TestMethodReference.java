package com.wv.testing;

import java.util.Comparator;

import test.Doublable;
import test.Sayable;

public class TestMethodReference {
	private String name = "";

	public TestMethodReference() {
		System.out.println("No args constructor was called");
	}

	public TestMethodReference(String name) {
		this.name = name;
	}

	public static void saySomething() {
		System.out.println("Hello, this is static method.");
	}

	public void nonSTATICsaySomething() {
		System.out.println("Hello,i am not static.... " + name);
	}

	public static int comparing(String a, String b) {
		return a.compareTo(b);
	}

	public static int printAndCompare(String a, String b) {
		System.out.println("just saying");
		return a.compareToIgnoreCase(b);
	}

	public static void main(String[] args) {
		// Referring static method
		Sayable sayable = TestMethodReference::saySomething;
		// Calling interface method
		sayable.say();

		sayable = () -> System.out.println("say again");
		sayable.say();

		Sayable sayingAgain22 = () -> {
			int i = 1;
		};
		sayingAgain22.say();// nothing will be printed

		// Comparator<String> compExample1 = (first,
		// second)->Integer.compare(first.length(), second.length());
		Comparator<String> compExample33 = TestMethodReference::comparing;
		int result = compExample33.compare("ZZxyaAAA3223", "5big");
		System.out.println(result);

		Comparator<String> compExample44 = TestMethodReference::printAndCompare;
		int result44 = compExample44.compare("ZZxyaAAA3223", "5big");
		System.out.println(result44);

		// ------------------------------------------------------------------------------
		// ------------------------------------------------------------------------------

		TestMethodReference tmr = new TestMethodReference();
		sayable = tmr::nonSTATICsaySomething;
		sayable.say();
		sayable = new TestMethodReference("mimo")::nonSTATICsaySomething;
		sayable.say();
		// ------------------------------------------------
		sayable = TestMethodReference::new;
		sayable.say();
		sayable.sayMyName("mahmoud");
		// ------------------------------------------------

		Thread th = new Thread(tmr::nonSTATICsaySomething);// Thread takes as input Runnable functional interface, which
															// we can implement using Lambda OR function reference (::)
		th.start();// start uses the implementation of Runnable interface as execution code
		new Thread(() -> System.err.println("one line instead of two")).run();
		// ---------------------------------------------------------

		Doublable doublable = Math::pow;// page 25 from java 8 for really impatient
		System.out.println(doublable.getResult(2.0, 3.0));
		doublable.printMyname();
	}
}