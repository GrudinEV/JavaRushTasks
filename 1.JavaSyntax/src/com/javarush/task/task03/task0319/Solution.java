package com.javarush.task.task03.task0319;

/* 
Предсказание на будущее
*/

import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        String name = Say(), sumS = Say(), ageS = Say();
        int sumI = Integer.parseInt(sumS), ageI = Integer.parseInt(ageS);
        System.out.println(name + " получает " + sumI + " через " + ageI + " лет.");

        //напишите тут ваш код

    }

    private static String Say() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String name = reader.readLine();
        return name;
    }
}
