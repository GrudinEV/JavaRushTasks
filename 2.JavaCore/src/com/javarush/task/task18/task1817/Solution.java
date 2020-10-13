package com.javarush.task.task18.task1817;

/* 
Пробелы
*/

import java.io.FileInputStream;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) throws IOException {
        FileInputStream inputStream = new FileInputStream(args[0]);
        int countSpace = 0, countAll = 0;
        while (inputStream.available() > 0) {
            countAll++;
            if (inputStream.read() == 32)
                countSpace++;
        }
        inputStream.close();
        float f = (float) Math.round(((float) countSpace / countAll) * 10000) / 100;
        System.out.println(f);
    }
}
