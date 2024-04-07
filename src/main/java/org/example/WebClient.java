package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


public class WebClient {
    public static final String LOCALHOST = "netology.homework";

    public static void main(String[] args) throws RuntimeException {
        try (Socket clientSocket = new Socket(LOCALHOST, WebServer.PORT);
             PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             Scanner send = new Scanner(System.in)) {
            for (int i = 0; i < 3; i++) {
                String line = reader.readLine();
                System.out.println(line);
                String name = send.nextLine();
                writer.println(name);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

