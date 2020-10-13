package com.javarush.task.task04.task0411;

/* 
Времена года на Терре
*/

public class Solution {
    public static void main(String[] args) {
        checkSeason(12);
        checkSeason(4);
        checkSeason(7);
        checkSeason(10);
    }

    public static void checkSeason(int month) {
        if ((month >= 3) && (month <= 8)) {
            if (month <= 5) System.out.println("весна"); else System.out.println("лето");
        }
        else {
            if ((month <= 11) && (month > 2)) System.out.println("осень"); else System.out.println("зима");
        }

        //напишите тут ваш код

    }
}