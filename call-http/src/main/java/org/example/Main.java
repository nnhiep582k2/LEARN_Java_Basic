package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {
    public static void main(String[] args) {
        try {
            // Create url
            URL url = new URL("https://api.quotable.io/quotes/random");
            // Open connection
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // Set method
            connection.setRequestMethod("GET");
            // Send request
            int responseCode = connection.getResponseCode();
            System.out.println("Response code: " + responseCode);
            // Read response
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            // Print result
            System.out.println(response.toString());
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}