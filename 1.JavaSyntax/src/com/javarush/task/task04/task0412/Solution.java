package com.javarush.task.task04.task0412;

/* 
Положительное и отрицательное число
*/

import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        while (true) {
            String a = reader.readLine();
            int b = Integer.parseInt(a);
            if (b > 0)
                System.out.println(b * 2);
            else
                if (b < 0)
                    System.out.println(b + 1);
                else
                    System.out.println(b);
//        }

    }

}