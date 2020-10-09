package com.javarush.task.task07.task0703;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* 
Общение одиноких массивов
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] listS = new String[10];
        int[] listI = new int[10];
        for (int i = 0; i < listS.length; i++) {
            listS[i] = reader.readLine();
            listI[i] = listS[i].length();
        }
        for (int i = 0; i < listI.length; i++)
            System.out.println(listI[i]);

    }
}
