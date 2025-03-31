package com.sfwe302.part2;

import java.io.IOException;

public class KwicTest {
    public static void main(String[] args) {

          try {
            Pipe inputToShifterPipe = new Pipe();
            Pipe shifterToAlphaPipe = new Pipe();
            Pipe alphaToOutPipe = new Pipe();

            InputFilter inputFilter = new InputFilter("input.txt", inputToShifterPipe.getWriter());
            CircularShifterFilter shifterFilter = new CircularShifterFilter(inputToShifterPipe.getReader(), shifterToAlphaPipe.getWriter());
            AlphabetizerFilter alphaFilter = new AlphabetizerFilter(shifterToAlphaPipe.getReader(), alphaToOutPipe.getWriter());
            OutputFilter outputFilter = new OutputFilter(alphaToOutPipe.getReader());

            Thread t1 = new Thread(inputFilter);
            Thread t2 = new Thread(shifterFilter);
            Thread t3 = new Thread(alphaFilter);
            Thread t4 = new Thread(outputFilter);

            t1.start();
            t2.start();
            t3.start();
            t4.start();

            t1.join();
            t2.join();
            t3.join();
            t4.join();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
