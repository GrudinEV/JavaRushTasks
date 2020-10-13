package com.javarush.task.task18.task1820;

/* 
Округление чисел
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = reader.readLine();
        String fileName2 = reader.readLine();
        reader.close();

        BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(fileName1));
        String str = "";
        while (inputStream.available() > 0) {
            str += (char) inputStream.read();
        }
        inputStream.close();
        String[] arrayString = str.split(" ");
        int[] arrayInt = new int[arrayString.length];
        for (int i = 0; i < arrayInt.length; i++) {
            arrayInt[i] = (int) Math.round(Float.parseFloat(arrayString[i]));
        }
        str = "";
        for (int i = 0; i < arrayInt.length; i++) {
            str += arrayInt[i] + " ";
        }
        byte[] arrayByte = str.getBytes();

        FileOutputStream outputStream = new FileOutputStream(fileName2);
        for (int b : arrayByte) {
            outputStream.write(b);
        }
        outputStream.close();

    }
}
