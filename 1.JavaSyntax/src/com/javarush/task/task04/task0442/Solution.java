package com.javarush.task.task04.task0442;

/* 
Суммирование
*/

import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        boolean b = false;
        int n = 0;
        while (!b) {
            int m = Integer.parseInt(reader.readLine());
            n += m;
            if (m == -1) b = true;
        }
        System.out.println(n);

    }
}
