package com.javarush.task.task08.task0817;

import java.util.*;

/* 
Нам повторы не нужны
*/

public class Solution {
    public static Map<String, String> createMap() {
        //напишите тут ваш код
        Map<String, String> map = new HashMap<>();
        map.put("Алексеева", "Ольга");
        map.put("Борисова", "Нина");
        map.put("Валеева", "Екатерина");
        map.put("Горобцова", "Мария");
        map.put("Дмитриева", "Ольга");
        map.put("Евсикова", "Евгения");
        map.put("Жилина", "Полина");
        map.put("Зимирева", "Екатерина");
        map.put("Иванова", "Олеся");
        map.put("Корнеева", "Екатерина");
        return map;

    }

    public static void removeTheFirstNameDuplicates(Map<String, String> map) {
        //напишите тут ваш код
        List<String> list = new ArrayList<>();
        for (String value : map.values()) list.add(value);
        int count;
        for (int i = 0; i < list.size();) {
            count = 1;
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(j) == list.get(i)) {
                    list.remove(j);
                    count++;
                }
            }
            if (count == 1) list.remove(i);
            else i++;
        }
        for (int i = 0; i < list.size(); i++) removeItemFromMapByValue(map, list.get(i));

    }

    public static void removeItemFromMapByValue(Map<String, String> map, String value) {
        Map<String, String> copy = new HashMap<>(map);
        for (Map.Entry<String, String> pair : copy.entrySet()) {
            if (pair.getValue().equals(value)) map.remove(pair.getKey());
        }
    }

    public static void main(String[] args) {
//        Map<String, String> map = createMap();
//        removeTheFirstNameDuplicates(map);
//        for (Map.Entry<String, String> pair : map.entrySet()) System.out.println(pair);

    }
}
