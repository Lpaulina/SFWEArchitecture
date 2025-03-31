package com.sfwe302.part2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

class CircularShifterFilter extends Filter {
    public CircularShifterFilter(PipedReader input, PipedWriter output) {
        super(input, output);
    }

    public void run() {
        try (BufferedReader reader = new BufferedReader(input);
             BufferedWriter writer = new BufferedWriter(output)) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+");
                for (int i = 0; i < words.length; i++) {
                    String shifted = shift(words, i);
                    writer.write(shifted);
                    writer.newLine();
                    writer.flush();
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String shift(String[] words, int index) {
        StringBuilder shifted = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            shifted.append(words[(index + i) % words.length]).append(" ");
        }
        return shifted.toString().trim();
    }
}