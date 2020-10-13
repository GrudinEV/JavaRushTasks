package com.javarush.task.task09.task0930;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Задача по алгоритмам
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> list = new ArrayList<>();
        while (true) {
            String s = reader.readLine();
            if (s.isEmpty()) {
                break;
            }
            list.add(s);
        }

        String[] array = list.toArray(new String[0]);
        sort(array);

        for (String x : array) {
            System.out.println(x);
        }
    }

    public static void sort(String[] array) {
        // напишите тут ваш код

        // Сортируем строки
        String string = null;
        int numString = 0;
        for (int i = 0; i < array.length; i++)
            if (!isNumber(array[i])) { string = array[i]; numString = i; break; };
        int num = numString;
        for (int i = array.length - 1; i > numString; i--) {
            for (int j = numString; j <= i; j++) {
                if (!isNumber(array[j]) && isGreaterThan(array[num], array[j])) {
                    array[num] = array[j];
                    array[j] = string;
                    num = j;
                }
                else if (!isNumber(array[j]) && isGreaterThan(array[j], array[num])) {
                    string = array[j];
                    num = j;
                }

            }
            num = numString;
            string = array[num];
        }

        //Сортируем числа по убыванию
        for (int i = 0; i < array.length; i++)
            if (isNumber(array[i])) { string = array[i]; numString = i; break; };
        num = numString;
        for (int i = array.length - 1; i > numString; i--) {
            for (int j = numString; j <= i; j++) {
                if (isNumber(array[j]) && Integer.parseInt(array[num]) < Integer.parseInt(array[j])) {
                    array[num] = array[j];
                    array[j] = string;
                    num = j;
                }
                else if (isNumber(array[j]) && Integer.parseInt(array[num]) > Integer.parseInt(array[j])) {
                    string = array[j];
                    num = j;
                }

            }
            num = numString;
            string = array[num];
        }
    }

    // Метод для сравнения строк: 'а' больше чем 'b'
    public static boolean isGreaterThan(String a, String b) {
        return a.compareTo(b) > 0;
    }


    // Переданная строка - это число?
    public static boolean isNumber(String s) {
        if (s.length() == 0) {
            return false;
        }

        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if ((i != 0 && c == '-') // Строка содержит '-'
                    || (!Character.isDigit(c) && c != '-') // или не цифра и не начинается с '-'
                    || (chars.length == 1 && c == '-')) // или одиночный '-'
            {
                return false;
            }
        }
        return true;
    }
}
