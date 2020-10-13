package com.javarush.task.task22.task2202;

/*
Найти подстроку
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getPartOfString(null));
    }

    public static String getPartOfString(String string) {
        if (string == null)
            throw new TooShortStringException();
        int[] array = new int[5];
        int k = 0;
        for (int i = 0; i < string.length(); i++) {
            if (k == 5) break;
            if (string.charAt(i) == ' ') {
                array[k] = i;
                k++;
            }
        }
        if (k < 4 || (k == 4 && string.length() == array[3] + 1)) {
            throw new TooShortStringException();
        } else if (k == 4) {
            return string.substring(array[0] + 1);
        } else {
            return string.substring(array[0] + 1, array[4]);
        }
    }

    public static class TooShortStringException extends RuntimeException{
    }
}
