package com.javarush.task.task03.task0303;

/* 
Обмен валют
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(convertEurToUsd(5, 1.2));
        System.out.println(convertEurToUsd(8, 1.2));
        //напишите тут ваш код

    }

    public static double convertEurToUsd(int eur, double course) {
        return eur * course;
        //напишите тут ваш код

    }
}
