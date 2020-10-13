package com.javarush.task.task19.task1908;

/* 
Выделяем числа
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader fileReader = new BufferedReader(new FileReader(reader.readLine()));
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter(reader.readLine()));
        reader.close();
        while (fileReader.ready()) {
            String[] array = fileReader.readLine().split(" ");
            for (String str : array) {
                byte[] b = str.getBytes();
                int length = 0;
                if (b[0] > 48 && b[0] < 58) {
                    length++;
                    if (b.length > 1) {
                        for (int i = 1; i < b.length; i++) {
                            if (b[i] > 47 && b[i] < 58)
                                length++;
                        }
                    }
                }
                if (length == str.length())
                    fileWriter.write(str + " ");
            }
        }
        fileReader.close();
        fileWriter.close();
    }
}
