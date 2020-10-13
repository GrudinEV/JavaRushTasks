package com.javarush.task.task04.task0441;

/* 
Как-то средненько
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
        if (((a1 >= a2) && (a1 <= a3)) || ((a1 >= a3) && (a1 <= a2))) System.out.println(a1);
        else if (((a2 >= a1) && (a2 <= a3)) || ((a2 >= a3) && (a2 <= a1))) System.out.println(a2); else
            System.out.println(a3);

    }
}
