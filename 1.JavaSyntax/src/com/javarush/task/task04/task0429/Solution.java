package com.javarush.task.task04.task0429;

/* 
Положительные и отрицательные числа
*/

import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int a1 = Integer.parseInt(reader.readLine());
        int a2 = Integer.parseInt(reader.readLine());
        int a3 = Integer.parseInt(reader.readLine());
        int m = 0, n = 0;
        if (a1 > 0) m++; else if (a1 < 0) n++;
        if (a2 > 0) m++; else if (a2 < 0) n++;
        if (a3 > 0) m++; else if (a3 < 0) n++;
        System.out.println("количество отрицательных чисел: " + n);
        System.out.println("количество положительных чисел: " + m);

    }
}
