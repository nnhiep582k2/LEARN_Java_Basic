package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            File file = new File("input.txt");
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("Existed: " + file.exists());
                System.out.println("Can read: " + file.canRead());
                System.out.println("Can write: " + file.canWrite());
                System.out.println("Can write: " + file.getAbsolutePath());
                FileWriter writer = new FileWriter("input.txt");
                writer.write("Hello nnhiep!\nNice to meet you!");
                writer.close();
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String data = scanner.nextLine();
                    System.out.println(data);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}