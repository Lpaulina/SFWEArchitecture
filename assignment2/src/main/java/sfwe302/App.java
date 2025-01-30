package sfwe302;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.itextpdf.kernel.pdf.PdfDocument; 
import com.itextpdf.kernel.pdf.PdfWriter; 
  
import com.itextpdf.layout.Document; 
import com.itextpdf.layout.element.Cell; 
import com.itextpdf.layout.element.Table; 

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        if (args.length == 0){
            System.out.println("Please input either PDF or XLS as an argument.");
        }

        String fileExtention = args[0];

        File file = new File("sample.cvs");
        
        if (!file.exists()){
            System.out.println("sample.cvs not found :(");
        }
        
        if (fileExtention.equalsIgnoreCase("PDF")){
           try {
            convertToPdf(file);
           } catch (Exception e) {
                System.out.println(e);
           }

        }
        else if (fileExtention.equalsIgnoreCase("XLS")){

        }
        else {
            System.out.println("Please submit a valid argument (PDF or XLS)");
        }

    }

    public static void convertToPdf(File file) throws IOException{

        PdfDocument pdf =  new PdfDocument(new PdfWriter("new.pdf"));
        Document doc = new Document(pdf);

        float[] columnWidths = {1,1,1,1,1};
        Table table = new Table(columnWidths);


        try (BufferedReader br = new BufferedReader(new FileReader(file))){

            String line;

            while ((line = br.readLine()) != null){
                String[] data = line.split(";");

                for (String word : data){
                    table.addCell(new Cell().add(word));
                }              
            }     
        } 

        doc.add(table);
        doc.close();
    }

    public static void convertToExcel(File file) throws IOException{
        
    }
}
