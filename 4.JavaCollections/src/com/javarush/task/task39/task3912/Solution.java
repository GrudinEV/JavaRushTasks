package com.javarush.task.task39.task3912;

/* 
Максимальная площадь
*/


import java.util.Date;

public class Solution {

    public static void main(String[] args) {
        int[][] matrix = new int[10000][10000];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = (int) (Math.random() * 2);
            }
        }
        Date dateStart = new Date();
        System.out.println(maxSquare(matrix));
        Date dateEnd = new Date();
        System.out.println(dateEnd.getTime() - dateStart.getTime());
    }

    public static int maxSquare(int[][] matrix) {
        int maxSideSquare = 0;
        for (int i = 0; i < matrix.length - maxSideSquare; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 1) {
                    if (maxSideSquare == 0) {
                        maxSideSquare = 1;
                    }
                    for (int dx = maxSideSquare; dx < Math.min(matrix.length - i, matrix[0].length - j); dx++) {
                        int countOnes = 0;
                        for (int m = i; m <= i + dx; m++) {
                            for (int n = j; n <= j + dx; n++) {
                                countOnes += matrix[m][n];
                            }
                        }
                        if ((countOnes == (dx + 1) * (dx + 1)) && ((dx + 1) > maxSideSquare)) {
                            maxSideSquare = dx + 1;
                        }
                        if (countOnes < (dx + 1) * (dx + 1)) {
                            break;
                        }
                    }
                }
            }
        }
        return maxSideSquare * maxSideSquare;
    }
}
