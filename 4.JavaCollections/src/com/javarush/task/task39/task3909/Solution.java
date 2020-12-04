package com.javarush.task.task39.task3909;

/* 
Одно изменение
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(isOneEditAway("leter", "letter"));
    }

    public static boolean isOneEditAway(String first, String second) {
        if (Math.abs(first.length() - second.length()) > 1) {
            return false;
        }
        if (first.equals(second)) {
            return true;
        }
        first = first + "xx";
        second = second + "xx";
        for (int i = 0; i < (Math.min(first.length(), second.length()) - 2); i++) {
            if (!first.substring(i, i + 1).equals(second.substring(i, i + 1))) {
                if (first.substring(i + 1).equals(second.substring(i))
                        || first.substring(i).equals(second.substring(i + 1))
                        || first.substring(i + 1).equals(second.substring(i + 1))) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
