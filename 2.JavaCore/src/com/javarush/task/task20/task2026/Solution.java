package com.javarush.task.task20.task2026;

/* 
Алгоритмы-прямоугольники
*/
public class Solution {
    public static void main(String[] args) {
        byte[][] a1 = new byte[][]{
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 1}
        };
        byte[][] a2 = new byte[][]{
                {1, 0, 0, 1},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {1, 0, 0, 1}
        };

        int count1 = getRectangleCount(a1);
        System.out.println("count = " + count1 + ". Должно быть 2");
        int count2 = getRectangleCount(a2);
        System.out.println("count = " + count2 + ". Должно быть 4");
    }

    public static int getRectangleCount(byte[][] a) {
        byte size = (byte) a.length;
        byte count = 0;
        for (byte i = 0; i < size; i++) {
            for (byte j = 0; j < size; j++) {
                if (a[i][j] == 1) {
                    if (i == 0 && j == 0) {
                        count++;
                        a[i][j] = (byte) (count + 1);
                    }
                    if (i == 0 && j > 0) {
                        if (a[i][j-1] > 1) {
                            a[i][j] = a[i][j-1];
                        } else {
                            count++;
                            a[i][j] = (byte) (count + 1);
                        }
                    }
                    if (i > 0 && j == 0) {
                        if (a[i-1][j] > 1) {
                            a[i][j] = a[i-1][j];
                        } else {
                            count++;
                            a[i][j] = (byte) (count + 1);
                        }
                    }
                    if (i > 0 && j > 0) {
                        if (a[i-1][j] > 1) {
                            a[i][j] = a[i-1][j];
                        } else {
                            if (a[i][j-1] > 1) {
                                a[i][j] = a[i][j-1];
                            } else {
                                count++;
                                a[i][j] = (byte) (count + 1);
                            }
                        }
                    }
                }
            }
        }
        return count;
    }
}
