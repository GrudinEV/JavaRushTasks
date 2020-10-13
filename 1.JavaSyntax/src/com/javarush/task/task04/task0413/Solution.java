package com.javarush.task.task04.task0413;

/* 
День недели
*/

import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String a = reader.readLine();
        int b = Integer.parseInt(a);
        if (b == 1) System.out.println("понедельник"); else {
            if (b == 2) System.out.println("вторник"); else {
                if (b == 3) System.out.println("среда"); else {
                    if (b == 4) System.out.println("четверг"); else {
                        if (b == 5) System.out.println("пятница"); else {
                            if (b == 6) System.out.println("суббота"); else {
                                if (b == 7) System.out.println("воскресенье"); else System.out.println("такого дня недели не существует");
                            }
                        }
                    }
                }
            }
        }


        //напишите тут ваш код

    }
}