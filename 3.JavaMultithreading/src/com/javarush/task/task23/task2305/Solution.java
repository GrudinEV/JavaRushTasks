package com.javarush.task.task23.task2305;

/* 
Inner
*/
public class Solution {
    public InnerClass[] innerClasses = new InnerClass[2];

    public class InnerClass {
    }

    public static Solution[] getTwoSolutions() {
        Solution[] array = new Solution[2];
        for (int i = 0; i < 2; i++) {
            array[i] = new Solution();
            for (int j = 0; j < 2; j++) {
                array[i].innerClasses[j] = array[i].new InnerClass();
            }
        }
        return array;
    }

    public static void main(String[] args) {

    }
}
