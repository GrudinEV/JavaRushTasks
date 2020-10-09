package com.javarush.task.task07.task0712;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Самые-самые
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        //напишите тут ваш код
        ArrayList<String> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int numberMin = 0,numberMax = 0;
        String s, sMin = "", sMax = "";
        for (int i = 0; i < 10; i++) {
            s = reader.readLine();
            if (i == 0) {
                sMin = sMax = s; numberMin = numberMax = i;
            }
            else {
                if (sMin.length() > s.length()) { sMin = s; numberMin = i;}
                if (sMax.length() < s.length()) { sMax = s; numberMax = i;}
            }
            list.add(s);
        }
        System.out.println(numberMin < numberMax ? list.get(numberMin) : list.get(numberMax));
    }
}
