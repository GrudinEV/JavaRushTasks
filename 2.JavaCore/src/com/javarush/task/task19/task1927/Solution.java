package com.javarush.task.task19.task1927;

/* 
Контекстная реклама
*/

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        PrintStream out = System.out;
        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(arrayOutputStream);
        System.setOut(stream);
        testString.printSomething();
        System.setOut(out);
        char[] array = arrayOutputStream.toString().toCharArray();
        int countEnter = 0;
        for (char c : array) {
            System.out.print(c);
            if (c == 10) {
                countEnter++;
                if (countEnter == 2) {
                    System.out.println("JavaRush - курсы Java онлайн");
                    countEnter = 0;
                }
            }
        }
        stream.close();
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("first");
            System.out.println("second");
            System.out.println("third");
            System.out.println("fourth");
            System.out.println("fifth");
        }
    }
}
