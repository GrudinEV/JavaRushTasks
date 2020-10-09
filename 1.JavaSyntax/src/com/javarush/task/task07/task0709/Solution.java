package com.javarush.task.task07.task0709;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Выражаемся покороче
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> list = new ArrayList<>();
        String s;
        int minLength = 0;
        for (int i = 0; i < 5; i++) {
            s = reader.readLine();
            list.add(s);
            minLength = minLength == 0 || minLength > s.length() ? s.length() : minLength;
        }

        for (int i = 0; i < list.size(); i++) {
            s = list.get(i);
            if (minLength == s.length()) System.out.println(s);
        }
    }
}
