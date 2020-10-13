package com.javarush.task.task18.task1809;

/* 
Реверс файла
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = reader.readLine();
        String fileName2 = reader.readLine();
        reader.close();
        FileInputStream inputStream = new FileInputStream(fileName1);
        byte[] buffer = new byte[inputStream.available()];
        int count = inputStream.read(buffer);
        inputStream.close();
        FileOutputStream outputStream = new FileOutputStream(fileName2);
        for (int i = count - 1; i >= 0; i--) {
            outputStream.write(buffer[i]);
        }
        outputStream.close();
    }
}
