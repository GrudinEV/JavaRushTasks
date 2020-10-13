package com.javarush.task.task18.task1807;

/* 
Подсчет запятых
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        FileInputStream inputStream = new FileInputStream(new BufferedReader(new InputStreamReader(System.in)).readLine());
        int countCommas = 0;
        while (inputStream.available() > 0) {
            if (inputStream.read() == 44) countCommas++;
        }
        inputStream.close();
        System.out.println(countCommas);
    }
}
