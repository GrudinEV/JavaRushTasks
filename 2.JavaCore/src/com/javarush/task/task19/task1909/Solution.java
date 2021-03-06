package com.javarush.task.task19.task1909;

/* 
Замена знаков
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader fileReader = new BufferedReader(new FileReader(reader.readLine()));
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter(reader.readLine()));
        reader.close();

        while (fileReader.ready()) {
            int symbol = fileReader.read();
            if (symbol == 46)
                fileWriter.write(33);
            else fileWriter.write(symbol);
        }

        fileReader.close();
        fileWriter.close();
    }
}
