package com.javarush.task.task19.task1925;

/* 
Длинные слова
*/

import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        ArrayList<String> list = new ArrayList<>();
        while (reader.ready()) {
            String[] array = reader.readLine().split("\\s");
            for (String word : array) {
                if (word.length() > 6) {
                    list.add(word + ",");
                }
            }
        }
        reader.close();
        list.set(list.size() - 1, list.get(list.size() - 1).substring(0, list.get(list.size() - 1).length() - 1));
        BufferedWriter writer = new BufferedWriter(new FileWriter(args[1]));

        for (String word : list) {
            writer.write(word);
        }

        writer.close();
    }
}
