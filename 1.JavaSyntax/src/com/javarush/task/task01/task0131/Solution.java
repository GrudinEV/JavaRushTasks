package com.javarush.task.task01.task0131;

/* 
Полнометражная картина
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(getMetreFromCentimetre(243));
    }

    public static int getMetreFromCentimetre(int centimetre) {
        //int n = 0, m = centimetre;
        //for (int i=0; m > 0; i++) {
        //m = m - 100;
            //n = i;
        //}
        return (centimetre / 100);
        //напишите тут ваш код
    }
}