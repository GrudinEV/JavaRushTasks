package com.javarush.task.task08.task0813;

import java.util.HashSet;
import java.util.Set;

/* 
20 слов на букву «Л»
*/

public class Solution {
    public static Set<String> createSet() {
        //напишите тут ваш код
        Set<String> set = new HashSet<>(20);
        for (int i = 0; i < 20; i++)
        set.add("Лето" + i);
        return set;

    }

    public static void main(String[] args) {

    }
}
