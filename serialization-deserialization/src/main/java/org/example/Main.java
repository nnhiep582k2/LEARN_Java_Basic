package org.example;

import java.io.*;

public class Main {
    public static class Person implements Serializable {
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() { return this.name; }

        public int getAge() { return this.age; }
    }

    public static void serialzeTest(Person person) {
        try {
            FileOutputStream fileOut = new FileOutputStream("output.txt");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(person);
            out.close();
            fileOut.close();
            System.out.println("Serialization completed.");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void deserialzation() {
        try {
            FileInputStream fileIn = new FileInputStream("output.txt");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Person result = (Person) in.readObject();
            in.close();
            fileIn.close();
            System.out.println("Name: " + result.getName());
            System.out.println("Age: " + result.getAge());
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Person person = new Person("nnhiep", 22);
        try {
            serialzeTest(person);
            deserialzation();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}