package com.javarush.task.task07.task0705;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* 
Один большой массив и два маленьких
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] listBig = new int[20];
        int[] listSmall1 = new int[10];
        int[] listSmall2 = new int[10];
        for (int i = 0; i < listBig.length; i++)
            listBig[i] = Integer.parseInt(reader.readLine());
        for (int i = 0; i < listSmall1.length; i++) {
            listSmall1[i] = listBig[i];
            listSmall2[i] = listBig[i + 10];
            System.out.println(listSmall2[i]);
        }

    }
}
