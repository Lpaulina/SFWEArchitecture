package com.sfwe302.part2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PipedReader;

class OutputFilter extends Filter {
    public OutputFilter(PipedReader input) {
        super(input, null);
    }

    public void run() {
        try (BufferedReader reader = new BufferedReader(input)) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line); // Print sorted output
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
