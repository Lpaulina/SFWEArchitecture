package com.sfwe302;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;

public class TestCreatePDF {

    private static final String CSVfile = "sample.csv";
    private static final String outputPdf = "new.pdf";

    @BeforeEach
    void setUp(){
        File file = new File("new.pdf");
        if (file.exists()){
            file.delete();
        }
    }

  
    @Test
    void testConvertToPdf_validCSV() throws IOException {
        File inputFile = new File(CSVfile);
        CreatePDF.convertToPdf(inputFile);

        File pdf = new File(outputPdf);
        assertTrue(pdf.exists());

        try (PdfDocument pdfDocument = new PdfDocument(new PdfReader(pdf))) {
            assertTrue(pdfDocument.getNumberOfPages() > 0, "The PDF should contain pages");

            String pageText = PdfTextExtractor.getTextFromPage(pdfDocument.getPage(1));

            assertTrue(pageText.contains("Name"));
            assertTrue(pageText.contains("Alice Johnson"));
            assertTrue(pageText.contains("Mathematics"));
        }
    }

    @Test
    void testConvertToPdf_emptyCSV() throws IOException {
        File emptyFile = new File("empty.csv");

        CreatePDF.convertToPdf(emptyFile);

        File outputFile = new File(outputPdf);
        assertTrue(outputFile.exists());

        try (PdfDocument pdfDocument = new PdfDocument(new PdfReader(outputFile))) {
            assertTrue(pdfDocument.getNumberOfPages() > 0, "The PDF should contain at least one page");

        }
    }
}
