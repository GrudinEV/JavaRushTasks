package com.javarush.task.task19.task1915;

/* 
Дублируем текст
*/

import java.io.*;
import java.net.URL;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) throws IOException {
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        String fileName = reader.readLine();
//        reader.close();
//        PrintStream out = System.out;
//        ByteArrayOutputStream array = new ByteArrayOutputStream();
//        PrintStream stream = new PrintStream(array);
//        System.setOut(stream);
//        testString.printSomething();
//        System.setOut(out);
//        FileOutputStream outputStream = new FileOutputStream(fileName);
//        outputStream.write(array.toString().getBytes());
//        outputStream.close();
//        System.out.println(array.toString());

        URL ru59 = new URL("https://59.ru/text/health/69058594/");
        BufferedReader reader = new BufferedReader(new InputStreamReader(ru59.openStream()));
        while (reader.ready()) {
            System.out.println(reader.readLine());
        }
        reader.close();
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("it's a text for testing");
        }
    }
}

