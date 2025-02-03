package com.sfwe302;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell; 
import org.apache.poi.ss.usermodel.Row; 
import org.apache.poi.xssf.usermodel.XSSFSheet; 
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CreateExcel {
    
    public static void convertToExcel(File file) throws IOException{
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("1");

        int rowNum = 0;
        int cellNum;

        try (BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;

            while ((line = br.readLine()) != null){
                Row row = sheet.createRow(rowNum++);
                cellNum = 0;

                String[] data = line.split(";");

                for (String word : data){
                    Cell cell = row.createCell(cellNum++);
                    cell.setCellValue(word);
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        try{
            FileOutputStream out = new FileOutputStream(new File("new.xls"));
            workbook.write(out);

            out.close();
        } catch (Exception e){
            e.printStackTrace();
        }

        workbook.close();
    }
}
