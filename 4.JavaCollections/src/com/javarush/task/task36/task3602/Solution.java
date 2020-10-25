package com.javarush.task.task36.task3602;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/* 
Найти класс по описанию Ӏ Java Collections: 6 уровень, 6 лекция
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getExpectedClass());
    }

    public static Class getExpectedClass() {
        for (Class clazz : Collections.class.getDeclaredClasses()) {
            if (clazz.toString().endsWith("EmptyList")) {
                return clazz;
            }
        }
        return Collections.class.getDeclaringClass();
    }
}
