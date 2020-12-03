package com.javarush.task.task39.task3904;

import java.util.Arrays;

/* 
Лестница
*/

public class Solution {
    private static int n = 70;

    public static void main(String[] args) {
        System.out.println("The number of possible ascents for " + n + " steps is: " + numberOfPossibleAscents(n));
    }

    public static long numberOfPossibleAscents(int n) {
        if (n < 0) {
            return 0;
        }
        if (n == 0) {
            return 1;
        }
        long count = 0;
        long a = 0;
        long b = 0;
        long c = 1;
        int i = 0;
        while (i < n) {
            count = a + b + c;
            a = b;
            b = c;
            c = count;
            i++;
        }
        return count;
    }
}