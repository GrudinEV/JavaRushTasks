package com.javarush.task.task04.task0416;

/* 
Переходим дорогу вслепую
*/

import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        double time = Double.parseDouble(reader.readLine());
        while (time > 5.0) {
            time = time - 5;
        }
        if ((time < 3) || (time == 5)) System.out.println("зелёный");
        else
            if (time < 4) System.out.println("жёлтый"); else System.out.println("красный");

    }
}