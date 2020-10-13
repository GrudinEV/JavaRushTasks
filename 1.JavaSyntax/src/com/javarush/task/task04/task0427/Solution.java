package com.javarush.task.task04.task0427;

/* 
Описываем числа
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
        if ((a > 0) && (a < 1000)) {
            if (b * 2 == a) m = "четное "; else m = "нечетное ";
            if (a < 10) n = "однозначное ";
            else if (a < 100) n = "двузначное "; else n = "трехзначное ";
            System.out.println(m + n + "число");
        }

    }
}
