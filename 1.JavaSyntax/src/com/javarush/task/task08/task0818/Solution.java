package com.javarush.task.task08.task0818;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* 
Только для богачей
*/

public class Solution {
    public static Map<String, Integer> createMap() {
        //напишите тут ваш код
        Map<String,Integer> map = new HashMap<>();
        map.put("Иванов", 300);
        map.put("Алексеев", 500);
        map.put("Бивнев", 800);
        map.put("Волегов", 400);
        map.put("Гончаров", 450);
        map.put("Дикий", 520);
        map.put("Ужов", 630);
        map.put("Ежов", 715);
        map.put("Жилов", 490);
        map.put("Кобзев", 660);
        return map;
    }

    public static void removeItemFromMap(Map<String, Integer> map) {
        //напишите тут ваш код
        Map<String, Integer> copy = new HashMap<>(map);
        Iterator<Map.Entry<String, Integer>> iterator = copy.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> pair = iterator.next();
            if (pair.getValue() < 500) map.remove(pair.getKey());
        }

    }

    public static void main(String[] args) {

    }
}