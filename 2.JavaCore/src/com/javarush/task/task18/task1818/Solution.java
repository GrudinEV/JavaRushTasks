package com.javarush.task.task18.task1818;

/* 
Два в одном
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = reader.readLine();
        String fileName2 = reader.readLine();
        String fileName3 = reader.readLine();
        reader.close();

        BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(fileName2));
        FileOutputStream outputStream = new FileOutputStream(fileName1);
        byte[] b = new byte[buffer.available()];
        buffer.read(b);
        outputStream.write(b);
        buffer.close();
        outputStream.close();

        BufferedInputStream buffer1 = new BufferedInputStream(new FileInputStream(fileName3));
        FileOutputStream outputStream1 = new FileOutputStream(fileName1, true);
        int c;
        while ((c = buffer1.read()) != -1) {
            outputStream1.write((char) c);
        }
        buffer1.close();
        outputStream1.close();

//        int b;
//        while ((b = buffer.read()) != -1) {
//            outputStream.write(b);
//        }
    }
}
