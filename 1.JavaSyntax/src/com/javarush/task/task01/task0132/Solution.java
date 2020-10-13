package com.javarush.task.task01.task0132;

/* 
Сумма цифр трехзначного числа
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(sumDigitsInNumber(546));
    }

    public static int sumDigitsInNumber(int number) {

        int a = number / 100, b = (number - a * 100) / 10, c = number - a * 100 - b * 10;
        return (a + b + c);
        //напишите тут ваш код
    }
}