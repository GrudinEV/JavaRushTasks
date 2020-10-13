package com.javarush.task.task07.task0721;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Минимаксы в массивах
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int maximum;
        int minimum;
        int[] array = new int[20];

        //напишите тут ваш код
        for (int i = 0; i < array.length; i++) array[i] = Integer.parseInt(reader.readLine());
        maximum = minimum = array[0];
        for (int i = 1; i < array.length; i++) {
            maximum = maximum < array[i] ? array[i] : maximum;
            minimum = minimum > array[i] ? array[i] : minimum;
        }
        System.out.print(maximum + " " + minimum);
    }
}
