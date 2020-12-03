package com.javarush.task.task39.task3909;

/* 
Одно изменение
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(isOneEditAway("asdfghj", "afsgdh"));
    }

    public static boolean isOneEditAway(String first, String second) {
        if (Math.abs(first.length() - second.length()) > 1) {
            return false;
        }
        return true;
    }
}
