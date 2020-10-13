package com.javarush.task.task09.task0927;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* 
Десять котов
*/

public class Solution {
    public static void main(String[] args) {
        Map<String, Cat> map = createMap();
        Set<Cat> set = convertMapToSet(map);
        printCatSet(set);
    }

    public static Map<String, Cat> createMap() {
        //напишите тут ваш код
        Map<String, Cat> map = new HashMap<>();
        map.put("Vasa", new Cat("Vasa"));
        map.put("Vaha", new Cat("Vaha"));
        map.put("Gera", new Cat("Gera"));
        map.put("Liska", new Cat("Liska"));
        map.put("Riska", new Cat("Riska"));
        map.put("Murka", new Cat("Murka"));
        map.put("Pushok", new Cat("Pushok"));
        map.put("Torchek", new Cat("Torchek"));
        map.put("Vaska", new Cat("Rizik"));
        map.put("Cnezok", new Cat("Belek"));
        return map;
    }

    public static Set<Cat> convertMapToSet(Map<String, Cat> map) {
        //напишите тут ваш код
        Set<Cat> set = new HashSet<>();
        set.addAll(map.values());
        return set;
    }

    public static void printCatSet(Set<Cat> set) {
        for (Cat cat : set) {
            System.out.println(cat);
        }
    }

    public static class Cat {
        private String name;

        public Cat(String name) {
            this.name = name;
        }

        public String toString() {
            return "Cat " + this.name;
        }
    }


}
