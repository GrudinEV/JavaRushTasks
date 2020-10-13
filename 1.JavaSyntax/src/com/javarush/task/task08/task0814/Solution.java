package com.javarush.task.task08.task0814;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* 
Больше 10? Вы нам не подходите
*/

public class Solution {
    public static Set<Integer> createSet() {
        // напишите тут ваш код
        Set<Integer> set = new HashSet<>(20);
        for (int i = 0; i < 20;) {
            Integer a = (int) (Math.random() * 30);
            if (set.contains(a)) i = i; else { set.add(a); i++; }
        }
        return set;

    }

    public static Set<Integer> removeAllNumbersGreaterThan10(Set<Integer> set) {
        // напишите тут ваш код
        Iterator<Integer> iterator = set.iterator();
        while (iterator.hasNext()) {
            Integer s = iterator.next();
            if (s > 10) iterator.remove();
        }
        return set;

    }

    public static void main(String[] args) {
//        Set<Integer> set = createSet();
//        Iterator<Integer> iterator = set.iterator();
//        for (Integer i : set) System.out.println(iterator.next());
//        System.out.println("");
//        Set<Integer> set1 = removeAllNumbersGreaterThan10(set);
//        Iterator<Integer> iterator1 = set1.iterator();
//        for (Integer i : set1) System.out.println(iterator1.next());

    }
}
