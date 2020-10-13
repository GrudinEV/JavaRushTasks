package com.javarush.task.task18.task1808;

/* 
Разделение файла
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = reader.readLine();
        String fileName2 = reader.readLine();
        String fileName3 = reader.readLine();
        reader.close();
        FileInputStream inputStream = new FileInputStream(fileName1);
        byte[] buffer = new byte[inputStream.available() / 2 + inputStream.available() % 2];
        FileOutputStream outputStream2 = new FileOutputStream(fileName2);
        int count = inputStream.read(buffer);
        outputStream2.write(buffer, 0, count);
        outputStream2.close();
        count = inputStream.read(buffer);
        inputStream.close();
        FileOutputStream outputStream3 = new FileOutputStream(fileName3);
        outputStream3.write(buffer,0,count);
        outputStream3.close();
    }
}
