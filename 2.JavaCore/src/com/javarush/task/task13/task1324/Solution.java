package com.javarush.task.task13.task1324;

/* 
Один метод в классе
*/

import javafx.scene.paint.Color;

public class Solution {
    public static void main(String[] args) throws Exception {
    }

    public interface Animal {
        Color getColor();

        Integer getAge();
    }

    public static abstract class Fox implements Animal{
        public String getName() {
            return "Fox";
        }
    }
}
