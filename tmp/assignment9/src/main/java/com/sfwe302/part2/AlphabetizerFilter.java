package com.sfwe302.part2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class AlphabetizerFilter extends Filter {
    public AlphabetizerFilter(PipedReader input, PipedWriter output) {
        super(input, output);
    }

    public void run() {
        List<String> shifts = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(input);
             BufferedWriter writer = new BufferedWriter(output)) {

            String line;
            while ((line = reader.readLine()) != null) {
                shifts.add(line);
            }

            Collections.sort(shifts); // Alphabetize shifts

            for (String sortedLine : shifts) {
                writer.write(sortedLine);
                writer.newLine();
                writer.flush();
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
