package com.javarush.task.task19.task1926;

/* 
Перевертыши
*/

import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();
        BufferedReader fileReader = new BufferedReader(new FileReader(fileName));

        while (fileReader.ready()) {
            char[] array = fileReader.readLine().toCharArray();
            for (int i = array.length - 1; i >=0; i--) {
                System.out.print(array[i]);
            }
            System.out.print("\n");
        }
        fileReader.close();
    }
}
