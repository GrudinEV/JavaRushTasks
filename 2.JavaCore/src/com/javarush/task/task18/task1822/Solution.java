package com.javarush.task.task18.task1822;

/* 
Поиск данных внутри файла
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
        String str;
        while ((str = reader.readLine()) != null) {
            if (str.substring(0, args[0].length() + 1).equals(args[0] + " ")) {
                System.out.println(str);
                break;
            }
        }
        reader.close();
    }
}
