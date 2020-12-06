package com.javarush.task.task39.task3912;

/* 
Максимальная площадь
*/

public class Solution {
    public static void main(String[] args) {
        int square = maxSquare(new int[][]{{1, 0, 1, 1, 1},
                              {1, 0, 1, 1, 1},
                              {0, 0, 1, 1, 1},
                              {1, 1, 1, 0, 1},
                              {0, 1, 1, 0, 1}});
        System.out.println(square);
    }

    public static int maxSquare(int[][] matrix) {
        int countOnes = 0;
        int height = matrix.length;
        int width = matrix[0].length;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                countOnes += matrix[i][j];
            }
        }
        if (countOnes == width * height) {
            return countOnes;
        }
        int maxCountOnes = 0;
        int maxSideOfSquare = Math.min(Math.min(matrix.length, matrix[0].length), (int) Math.sqrt(countOnes));
        for (int i = 0; i < height - maxSideOfSquare; i++) {
            for (int j = 0; j < width - maxSideOfSquare; j++) {
                int miniMatrix[][] = new int[maxSideOfSquare][maxSideOfSquare];
                for (int m = 0; m < maxSideOfSquare; m++) {
                    for (int n = 0; n < maxSideOfSquare; n++) {
                        miniMatrix[m][n] = matrix[i + m][j + n];
                    }
                }
                int square = maxSquare(miniMatrix);
                maxCountOnes = Math.max(maxCountOnes, square);
            }
        }
//        System.out.println(maxSideOfSquare);
        return maxCountOnes;
    }
}
