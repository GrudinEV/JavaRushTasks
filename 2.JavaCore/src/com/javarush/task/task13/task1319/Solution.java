package com.javarush.task.task13.task1319;

/* 
Писатель в файл с консоли
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        // напишите тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();

        BufferedWriter writeToFile = new BufferedWriter(new FileWriter(fileName));//открытие буфера записи в файл

        while (true) {
            String string = reader.readLine();
            writeToFile.write(string);
            writeToFile.newLine();
            if (string.equals("exit")) break;
        }

        reader.close();
        writeToFile.close();
    }
}
