package com.javarush.task.task18.task1801;

/* 
Максимальный байт
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream fileInputStream = new FileInputStream(reader.readLine());
        reader.close();
        int maxByte = 0;
        while (fileInputStream.available() > 0) {
            int b = fileInputStream.read();
            maxByte = maxByte < b ? b : maxByte;
        }
        fileInputStream.close();
        System.out.println(maxByte);

    }
}
