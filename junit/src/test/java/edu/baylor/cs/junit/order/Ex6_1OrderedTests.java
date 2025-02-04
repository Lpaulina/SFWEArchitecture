package edu.baylor.cs.junit.order;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Ex6_1OrderedTests {

	@Test
	void bSucceedingTest2() {
		System.out.println("I am numero dos");
	}

	@Test
	void cSucceedingTest1() {
		System.out.println("I am numero uno");
	}

	@Test
	void aSucceedingTest3() {
		System.out.println("I am numero tres");
	}
}