package com.sfwe302;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestCreateExcel {

    private static final String validCSV = "sample.csv";
    private static final String outputXLS = "new.xls";

    @BeforeEach
    void setUp(){
        File file = new File("new.xls");
        if (file.exists()){
            file.delete();
        }
    }

    @Test
    void testValidExcelData() throws IOException{
        File inputFile = new File(validCSV);
        CreateExcel.convertToExcel(inputFile);

        File outputFile = new File(outputXLS);

        XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(outputFile));
        XSSFSheet sheet = workbook.getSheetAt(0);

        XSSFRow row = sheet.getRow(0);
        assertEquals("ID", row.getCell(0).getStringCellValue());
        assertEquals("Name", row.getCell(1).getStringCellValue());
        assertEquals("Age", row.getCell(2).getStringCellValue());
        assertEquals("Major", row.getCell(3).getStringCellValue());
        assertEquals("GPA", row.getCell(4).getStringCellValue());

        row = sheet.getRow(1);

        assertEquals("101", row.getCell(0).getStringCellValue());
        assertEquals("Alice Johnson", row.getCell(1).getStringCellValue());
        assertEquals("20", row.getCell(2).getStringCellValue());
        assertEquals("Computer Science", row.getCell(3).getStringCellValue());
        assertEquals("3.8", row.getCell(4).getStringCellValue());


        workbook.close();
    }

    @Test
    void testConvertToExcelWithEmptyFile() throws IOException {
        
        File emptyFile = new File("empty.csv");
        CreateExcel.convertToExcel(emptyFile);

        File outputFile = new File(outputXLS);
        assertTrue(outputFile.exists());

        try (XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(outputFile))) {
            XSSFSheet sheet = workbook.getSheetAt(0);
            assertEquals(0, sheet.getPhysicalNumberOfRows());
        }
    }
}
