package com.sfwe302;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

public class CreatePDF{
    
    public static void convertToPdf(File file) throws IOException{

        PdfDocument pdf =  new PdfDocument(new PdfWriter("new.pdf"));
        Document doc = new Document(pdf);

        Boolean firstRow = true;
        Table table = new Table(1);


        try (BufferedReader br = new BufferedReader(new FileReader(file))){

            String line;

            while ((line = br.readLine()) != null){
                String[] data = line.split(";");

                if (firstRow){
                    firstRow = false;
                    float[] columns = new float[data.length];

                    for (int i = 0; i < data.length; i++){
                        columns[i] = 1;
                    }
                    table = new Table(columns);
                    
                }

                for (String word : data){
                    table.addCell(new Cell().add(new Paragraph(word)));
                }              
            }     
        } 

        doc.add(table);
        doc.close();
    }
}
