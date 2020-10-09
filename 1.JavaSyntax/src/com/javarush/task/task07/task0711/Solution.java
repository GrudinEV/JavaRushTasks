package com.javarush.task.task07.task0711;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Удалить и вставить
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> list = new ArrayList<>();
        String s;
        for (int i = 0; i < 5; i++) {
            s = reader.readLine();
            list.add(s);
        }
        for (int i = 1; i <= 13; i++) {
            s = list.get(list.size() - 1);
            list.remove(list.size() - 1);
            list.add(0, s);
        }
        for (int i = 0; i < list.size(); i++)
            System.out.println(list.get(i));
    }
}
