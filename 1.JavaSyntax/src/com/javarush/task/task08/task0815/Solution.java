package com.javarush.task.task08.task0815;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* 
Перепись населения
*/

public class Solution {
    public static Map<String, String> createMap() {
        //напишите тут ваш код
        Map<String, String> map = new HashMap<>();
        map.put("Козлова", "Вика");
        map.put("Ташлыкова", "Настя");
        map.put("Иванькова", "Женя");
        map.put("Калашникова", "Таня");
        map.put("Грудин", "Женя");
        map.put("Калашников", "Сергей");
        map.put("Сергеева", "Катя");
        map.put("Ташлыков", "Саша");
        map.put("Ерёмин", "Гриша");
        map.put("Грудина", "Мира");
        return map;

    }

    public static int getCountTheSameFirstName(Map<String, String> map, String name) {
        //напишите тут ваш код
        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
        int i = 0;
        while (iterator.hasNext()) {
            Map.Entry<String, String> pair = iterator.next();
            if(pair.getValue() == name) i++;
        }
        return i;

    }

    public static int getCountTheSameLastName(Map<String, String> map, String lastName) {
        //напишите тут ваш код
        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
        int i = 0;
        while (iterator.hasNext()) {
            Map.Entry<String, String> pair = iterator.next();
            if (pair.getKey() == lastName) i++;
        }
        return i;

    }

    public static void main(String[] args) {
        Map<String, String> map = createMap();
//        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
//        while (iterator.hasNext()) System.out.println(iterator.next());
//        int i = getCountTheSameFirstName(map,"Женя");
//        System.out.println(i);

    }
}
