package com.sfwe302.part3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class CountriesClient {
      public static void main(String[] args) throws IOException, InterruptedException {

        String hostName = args[0];
        int portNumber = Integer.parseInt(args[1]);

        // while (true) {
            try {
                Socket countriesSocket = new Socket(hostName, portNumber);
                PrintWriter sockOut = new PrintWriter(countriesSocket.getOutputStream(), true);
                BufferedReader sockIn = new BufferedReader(new InputStreamReader(countriesSocket.getInputStream()));
                BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

                Thread serverListener = new Thread(() -> {
                    try {
                        String serverOutput;
                        while ((serverOutput = sockIn.readLine()) != null) {
                            System.out.println("Server: " + serverOutput);
                        }
                        countriesSocket.close();
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

                countriesSocket.close();

            } catch (IOException e) {
                System.err.println("Connection error: " + e.getMessage());
            }

        // }
    }
}
