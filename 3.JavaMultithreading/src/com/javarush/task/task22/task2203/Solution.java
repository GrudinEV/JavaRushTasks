package com.javarush.task.task22.task2203;

/* 
Между табуляциями
*/
public class Solution {

    public static String getPartOfString(String string) throws TooShortStringException {
        if (string == null)
            throw new TooShortStringException();
        String[] array = string.split("\t");
        if (array.length > 2) {
            return array[1];
        } else {
            throw new TooShortStringException();
        }
    }

    public static class TooShortStringException extends Exception {
    }

    public static void main(String[] args) throws TooShortStringException {
        System.out.println(getPartOfString(null/*"\tJavaRush - лучший сервис \tобучения Java\t."*/));
    }
}
