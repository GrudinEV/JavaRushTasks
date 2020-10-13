package com.javarush.task.task18.task1821;

/* 
Встречаемость символов
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        int[] array = new int[128];
        BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(args[0]));
        while (inputStream.available() > 0) {
            array[inputStream.read()]++;
        }
        inputStream.close();
        for (int i = 0; i < array.length; i++) {
            if (array[i] != 0) System.out.println((char) i + " " + array[i]);
        }

    }
}
