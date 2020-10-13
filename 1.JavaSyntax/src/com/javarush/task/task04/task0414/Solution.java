package com.javarush.task.task04.task0414;

/* 
Количество дней в году
*/

import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int year = Integer.parseInt(reader.readLine());
        int del400 = year / 400, del100 = year / 100, del4 = year / 4;
        int d400 = del400 * 400, d100 = del100 * 100, d4 = del4 * 4;
        if (d400 == year) System.out.println("количество дней в году: 366");
        else
            if (d100 == year) System.out.println("количество дней в году: 365");
            else
                if (d4 == year) System.out.println("количество дней в году: 366");
                else System.out.println("количество дней в году: 365");

    }
}