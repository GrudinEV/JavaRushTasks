package com.javarush.task.task18.task1805;

/* 
Сортировка байт
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeSet;

public class Solution {
    public static void main(String[] args) throws Exception {
        TreeSet<Integer> set = new TreeSet<>();
        FileInputStream inputStream = new FileInputStream(new BufferedReader(new InputStreamReader(System.in)).readLine());
        while (inputStream.available() > 0) {
            set.add(inputStream.read());
        }
        inputStream.close();
        for (Integer s : set) {
            System.out.print(s + " ");
        }
    }
}
