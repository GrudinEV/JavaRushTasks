package com.javarush.task.task04.task0415;

/* 
Правило треугольника
*/

import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String a1 = reader.readLine();
        int x1 = Integer.parseInt(a1);
        String a2 = reader.readLine();
        int x2 = Integer.parseInt(a2);
        String a3 = reader.readLine();
        int x3 = Integer.parseInt(a3);
        if ((x1 < (x2 + x3)) && (x2 < (x1 + x3)) && (x3 < (x1 + x2)))
            System.out.println("Треугольник существует.");
        else
            System.out.println("Треугольник не существует.");

        //напишите тут ваш код

    }
}