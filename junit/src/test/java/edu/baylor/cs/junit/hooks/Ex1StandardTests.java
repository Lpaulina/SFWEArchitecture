package edu.baylor.cs.junit.hooks;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class Ex1StandardTests {

    @BeforeAll
    static void initAll() {
    		System.out.println("initAll");
    }

    @BeforeEach
    void init() {
    		System.out.println("initEach");
    }

    @Test
    void succeedingTest() {
    }

    @Test
    void failingTest() {
        fail("a failing test");
    }

    @Test
    @Disabled("for demonstration purposes")
    void skippedTest() {
        // not executed
    }

    @AfterEach
    void tearDown() {
    	System.out.println("afterEach");
    }

    @AfterAll
    static void tearDownAll() {
    		System.out.println("afterAll");
    }

}