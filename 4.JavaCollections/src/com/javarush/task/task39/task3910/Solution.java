package com.javarush.task.task39.task3910;

/* 
isPowerOfThree
*/

public class Solution {
    public static void main(String[] args) {

    }

    public static boolean isPowerOfThree(int n) {
        if (n == 0) {
            return false;
        }
        if (n == 1) {
            return true;
        }
        if (n % 3 == 0) {
            if (n == 3) {
                return true;
            } else {
                n /= 3;
                return isPowerOfThree(n);
            }
        }
        return false;
    }
}
