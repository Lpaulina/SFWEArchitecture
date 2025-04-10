package edu.baylor.cs.junit.tag;

import static org.junit.Assert.fail;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import org.junit.jupiter.api.Disabled;


@Tag("debug")
public class Ex8TagTest {
	@Test
	@Disabled
	@Tag("production")
	void testCaseA(TestInfo testInfo) {
		fail("production");
	}

	@Test
	@Disabled
	void testCaseB(TestInfo testInfo) {
		fail("debug");
	}
}