package com.sfwe302.part1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class IceCreamClient {

    public static void main(String[] args) throws IOException, InterruptedException {

        String hostName = args[0];
        int portNumber = Integer.parseInt(args[1]);

        // while (true) {
            try {
                Socket iceCreamSocket = new Socket(hostName, portNumber);
                PrintWriter sockOut = new PrintWriter(iceCreamSocket.getOutputStream(), true);
                BufferedReader sockIn = new BufferedReader(new InputStreamReader(iceCreamSocket.getInputStream()));
                BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

                Thread serverListener = new Thread(() -> {
                    try {
                        String serverOutput;
                        while ((serverOutput = sockIn.readLine()) != null) {
                            System.out.println("Server: " + serverOutput);
                        }
                        iceCreamSocket.close();
                    } catch (IOException e) {
                        System.err.println("Error reading from server: " + e.getMessage());
                    }
                });

                serverListener.start();

                String userInput;
                while ((userInput = stdIn.readLine()) != null) {
                    if (userInput.contains("bye")) {
                        sockOut.println(userInput);
                        System.out.println("Client: " + userInput);
                        break;
                    }
                    sockOut.println(userInput);
                    System.out.println("Client: " + userInput);
                }
                iceCreamSocket.close();

            } catch (IOException e) {
                System.err.println("Connection error: " + e.getMessage());
            }

        // }
    }
}
