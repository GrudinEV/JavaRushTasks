package com.javarush.task.task04.task0426;

/* 
Ярлыки и числа
*/

import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int a = Integer.parseInt(reader.readLine());
        int b = a / 2;
        String m, n;
        if (a == 0) System.out.println("ноль");
        else {
            if (a > 0) m = "положительное "; else m = "отрицательное ";
            if (b * 2 == a) n = "четное "; else n = "нечетное ";
            System.out.println(m + n + "число");
        }


    }
}
