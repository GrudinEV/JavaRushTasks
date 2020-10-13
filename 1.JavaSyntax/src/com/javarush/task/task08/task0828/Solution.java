package com.javarush.task.task08.task0828;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/* 
Номер месяца
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        //напишите тут ваш код
        List<String> list = new ArrayList<String>();
        list.add("January");
        list.add("February");
        list.add("March");
        list.add("April");
        list.add("May");
        list.add("June");
        list.add("July");
        list.add("August");
        list.add("September");
        list.add("October");
        list.add("November");
        list.add("December");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        boolean b = true;
        int n;
        for (; b;) {
            String month = reader.readLine();
            if (list.contains(month)){
                n = list.indexOf(month) + 1;
                System.out.println(month + " is the " + n + " month");
                break;
            }
        }
    }
}
