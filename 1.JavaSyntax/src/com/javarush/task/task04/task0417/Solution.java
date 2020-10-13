package com.javarush.task.task04.task0417;

/* 
Существует ли пара?
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
        if ((a1 == a2) && (a1 == a3)) {
             System.out.print(a1 + " " + a2 + " " + a3);

        }
        else
            if (a1 == a2) out(a1, a2);
            else
                if (a1 == a3) out(a1, a3);
                else
                    if (a2 == a3) out (a2, a3);


    }

    private static void out(int a, int b) {
        System.out.print(a + " " + b);
    }

}