package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            // Create connection
            ServerSocket serverSocket = new ServerSocket(Common.PORT);
            System.out.println("Server is running on port " + Common.PORT);
            // Accept connection
            Socket clientSocket = serverSocket.accept();
            System.out.println("Conntected to client " + clientSocket.getInetAddress().getHostAddress());
            // In/Out stream
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            // Read from client
            String message = in.readLine();
            System.out.println("Message from client: " + message);
            // Send to client
            out.println("Hello, I'm Mr.Server!");
            // Close connection
            clientSocket.close();
            serverSocket.close();
        } catch(IOException e) {
            e.printStackTrace();
            System.err.println("I/O Exception: " + e);
        }
    }
}
