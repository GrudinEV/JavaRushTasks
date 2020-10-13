package com.javarush.task.task19.task1907;

/* 
Считаем слово
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader fileReader = new BufferedReader(new FileReader(reader.readLine()));
        reader.close();
        int countWorld = 0;
        while (fileReader.ready()) {
            String str = "," + fileReader.readLine() + ",";
            String[] array = str.split("\\bworld\\b");
            countWorld += (array.length - 1);
        }
        fileReader.close();
        System.out.println(countWorld);
    }
}
