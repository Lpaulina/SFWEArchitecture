package com.sfwe302.part3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class CountriesServer {
        public static void main(String[] args) throws IOException, InterruptedException {
        int portNumber = Integer.parseInt(args[0]);
        // while (true) {
            try {
                ServerSocket serverSocket = new ServerSocket(portNumber);

                Socket clientSocket = serverSocket.accept(); // blocking
                PrintWriter sockOut = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader sockIn = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));
                {
                    String inputLine, outputLine;

                    CountriesProtocol protocol = new CountriesProtocol();
                    sockOut.println(protocol.processInput(null));

                    while ((inputLine = sockIn.readLine()) != null) {
                        outputLine = protocol.processInput(inputLine);

                        String[] outputLines = outputLine.split("\n");
                        for (String line : outputLines) {
                            sockOut.println(line);
                        }
                    }
                    clientSocket.close();
                }

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        // }
    }
}
