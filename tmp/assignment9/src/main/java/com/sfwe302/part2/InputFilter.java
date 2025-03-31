package com.sfwe302.part2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.PipedWriter;

class InputFilter extends Filter {
    private BufferedReader fileReader;

    public InputFilter(String fileName, PipedWriter output){
        super(null, output);
        
        try {
            this.fileReader = new BufferedReader(new FileReader(fileName));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run(){
        try (BufferedWriter writer = new BufferedWriter(output)){
            String line;
            while ((line = fileReader.readLine())!= null){
                writer.write(line);
                writer.newLine();
                writer.flush();
            }
            writer.close();
        } catch (IOException error){
            error.printStackTrace();
        }
    }
}
