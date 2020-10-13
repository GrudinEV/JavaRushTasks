package com.javarush.task.task04.task0420;

/* 
Сортировка трех чисел
*/

import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int i1 = Integer.parseInt(reader.readLine());
        int i2 = Integer.parseInt(reader.readLine());
        int i3 = Integer.parseInt(reader.readLine());
        int a1, a2, a3;
        if ((i1 >= i2 ) && (i1 >= i3)) {
            if (i2 >= i3) System.out.println(i1 + " " + i2 + " " + i3); else System.out.println(i1 + " " + i3 + " " + i2);
        }
        else
            if ((i2 >= i1 ) && (i2 >= i3)) {
                if (i1 >= i3) System.out.println(i2 + " " + i1 + " " + i3); else System.out.println(i2 + " " + i3 + " " + i1);
            }
            else
                if (i1 >= i2) System.out.println(i3 + " " + i1 + " " + i2); else System.out.println(i3 + " " + i2 + " " + i1);

    }
}
