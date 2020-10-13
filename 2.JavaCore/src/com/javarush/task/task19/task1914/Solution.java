package com.javarush.task.task19.task1914;

/* 
Решаем пример
*/

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        PrintStream out = System.out;
        ByteArrayOutputStream array = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(array);
        System.setOut(stream);
        testString.printSomething();
        System.setOut(out);
        String str = array.toString();
        String[] arrayMathExample = str.split("\\s");
        String str1 = "";
        for (int i = 0; i < arrayMathExample.length; i++)
            str1 += (arrayMathExample[i] + " ");
        if (arrayMathExample[1].equals("+")) {
            System.out.println(str1 + (Integer.parseInt(arrayMathExample[0]) + Integer.parseInt(arrayMathExample[2])));
        } else if (arrayMathExample[1].equals("-")){
            System.out.println(str1 + (Integer.parseInt(arrayMathExample[0]) - Integer.parseInt(arrayMathExample[2])));
        } else {
            System.out.println(str1 + (Integer.parseInt(arrayMathExample[0]) * Integer.parseInt(arrayMathExample[2])));
        }

    }

    public static class TestString {
        public void printSomething() {
            System.out.println("3 + 6 = ");
        }
    }
}

