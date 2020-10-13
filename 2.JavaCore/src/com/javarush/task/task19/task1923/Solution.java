package com.javarush.task.task19.task1923;

/* 
Слова с цифрами
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader fileReader = new BufferedReader(new FileReader(args[0]));
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter(args[1]));
        while (fileReader.ready()) {
            String[] arrayWords = fileReader.readLine().split("\\s");
            for (String word : arrayWords) {
                String wordExtended = " " + word + " ";
                String[] arrayWord = wordExtended.split("\\d", 2);
                if (arrayWord.length == 2)
                    fileWriter.write(word + " ");
            }
        }

        fileReader.close();
        fileWriter.close();
    }
}
