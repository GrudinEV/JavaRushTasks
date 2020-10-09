package com.javarush.task.task07.task0706;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Улицы и дома
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        //напишите тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] list = new int[15];
        int even = 0, odd = 0;
        for (int i = 0; i < list.length; i++) {
            list[i] = Integer.parseInt(reader.readLine());
            if (i % 2 == 0) even += list[i]; else odd += list[i];
        }
        if (even != odd) System.out.println(even > odd ? "В домах с четными номерами проживает больше жителей." : "В домах с нечетными номерами проживает больше жителей.");
    }
}
