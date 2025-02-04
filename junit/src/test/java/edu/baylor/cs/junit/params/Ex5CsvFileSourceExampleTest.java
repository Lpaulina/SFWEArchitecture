package edu.baylor.cs.junit.params;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

@DisplayName("Should pass the method parameters provided by the test-data.csv file")
public class Ex5CsvFileSourceExampleTest {
 
    @DisplayName("Should calculate the correct sum")
    @ParameterizedTest(name = "{index} => a={0}, b={1}, sum={2}")
    @CsvFileSource(resources = "/test-data.csv")
    void sum(int a, int b, int sum) {
        assertEquals(sum, a + b);
    }
}