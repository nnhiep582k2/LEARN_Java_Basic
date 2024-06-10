package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class Client {
    public static void main(String[] args) {
        try {
            // Create connection
            Socket socket = new Socket(Common.HOST, Common.PORT);
            System.out.println("Connected to server " + socket.getInetAddress().getHostAddress());
            // In/Out stream
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // Send to server
            out.println("Hello, I'm Mrs.Client");
            // Read from server
            String response = in.readLine();
            System.out.println("Server response: " + response);
            // Close connection
            socket.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
