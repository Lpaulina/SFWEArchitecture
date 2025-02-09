package com.sfwe302;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import com.itextpdf.io.source.ByteArrayOutputStream;

public class TestApp {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();


    @BeforeEach
    void setUp(){
        System.setOut(new PrintStream(outputStream));
    }
    @Test
    void testIncorrectAmountArguments(){
        String[] args = {"pdf"};
        App.main(args);

        assertEquals("Please input either PDF or XLS as an argument as well as the name of the csv file." + System.lineSeparator(), outputStream.toString());

    }

    @Test 
    void testIncorrectFileExtension(){
        String[] args = {"ldf", "sample.csv"};
        App.main(args);

        assertEquals("Please submit a valid argument (PDF or XLS)" + System.lineSeparator(), outputStream.toString());

    }

    @Test
    void testIncorrectFile(){
        String[] args = {"pdf", "example.csv"};
        App.main(args);
        
        assertEquals("example.csv not found :(" + System.lineSeparator(), outputStream.toString());  
    }

    @Test
    void testPdfExists(){
        String[] args = {"pdf", "sample.csv"};
        App.main(args);

        File file = new File("new.pdf");
        assertTrue(file.exists());
    }

    @Test 
    void testExcelExists(){
        String[] args = {"xls", "sample.csv"};
        App.main(args);

        File file = new File("new.xls");
        assertTrue(file.exists());
    }

    @AfterEach
    void tearDown() {
        System.setOut(System.out);
    }

}
