package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class WebServer {
    public static final Integer PORT = 8080;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("New connection accepted");
            while (true) {
                Socket socket = serverSocket.accept();// ждем подключения
                try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                    out.println("Сonnection established. Write your name ?");
                    String line = in.readLine();
                    out.printf("Hi %s, Are you child? (yes/no)%n", line);
                    String yesOrNo = in.readLine();
                    if (yesOrNo.equals("no")) {
                        out.println(String.format(" Welcome to the adult zone, %s! Have a good rest, or a good working day!", line));
                    } else if (yesOrNo.equals("yes")) {
                        out.println(String.format(" Welcome to the kids area, %s! Let's play!", line));
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}