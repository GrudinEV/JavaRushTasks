package com.javarush.task.task04.task0433;

/* 
Гадание на долларовый счет
*/

public class Solution {
    public static void main(String[] args) {
        //напишите тут ваш код
        int i = 1, j = 1;
        while (i <= 10) {
            while (j <= 10) {
                System.out.print("S");
                j++;
            }
            System.out.print("\n");
            i++;
            j=1;
        }

    }
}
