package com.javarush.task.task39.task3905;

import java.util.Random;

/* 
Залей меня полностью
*/

public class Solution {
    public static void main(String[] args) {
        Color[][] image = new Color[10][10];
        for (int i = 0; i < image[0].length; i++) {
            for (int j = 0; j < image.length; j++) {
                image[i][j] = Color.values()[(int) (Math.random() * 7)];
                System.out.print(image[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println("------------***------------");
        System.out.println(new PhotoPaint().paintFill(image, 5, 5, Color.ORANGE));
        System.out.println("------------***------------");

        for (int i = 0; i < image[0].length; i++) {
            for (int j = 0; j < image.length; j++) {
                System.out.print(image[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
