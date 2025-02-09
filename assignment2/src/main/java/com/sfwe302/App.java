package com.sfwe302;
import java.io.File;


public class App {
    public static void main(String[] args) {

        if (args.length < 2){
            System.out.println("Please input either PDF or XLS as an argument as well as the name of the csv file.");
            return;
        }

        String fileExtention = args[0];
        String fileName = args[1];

        File file = new File(fileName);
        
        if (!file.exists()){
            System.out.println(fileName + " not found :(");
            return;
        }
        
        if (fileExtention.equalsIgnoreCase("PDF")){
           try {
            CreatePDF.convertToPdf(file);
           } catch (Exception e) {
                System.out.println(e);
           }

        }
        else if (fileExtention.equalsIgnoreCase("XLS")){
            try{
                CreateExcel.convertToExcel(file);
            }catch(Exception e){
                System.out.println(e);
            }
        }
        else {
            System.out.println("Please submit a valid argument (PDF or XLS)");
        }

    }

}
