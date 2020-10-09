package com.javarush.task.task07.task0708;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Самая длинная строка
*/

public class Solution {
    private static ArrayList<String> strings;

    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
//        ArrayList<String> longString = new ArrayList<>();
        strings = new ArrayList<>();
        int maxLength = 0;
        String s;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 5; i++) {
            s = reader.readLine();
            strings.add(s);
            maxLength = maxLength < s.length() ? s.length() : maxLength;
        }
        for (int i = 0; i < strings.size(); i++) {
            s = strings.get(i);
            if (maxLength == s.length()) System.out.println(s);
        }

    }
}
