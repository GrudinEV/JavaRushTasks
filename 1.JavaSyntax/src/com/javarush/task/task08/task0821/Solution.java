package com.javarush.task.task08.task0821;

import java.util.HashMap;
import java.util.Map;

/* 
Однофамильцы и тёзки
*/

public class Solution {
    public static void main(String[] args) {
        Map<String, String> map = createPeopleMap();
        printPeopleMap(map);
    }

    public static Map<String, String> createPeopleMap() {
        //напишите тут ваш код
        Map<String, String> map = new HashMap<>();
        map.put("Алексеев", "Сергей");
        map.put("Борисов", "Валерий");
        map.put("Волегов", "Игорь");
        map.put("Алексеев", "Руслан");
        map.put("Ганеев", "Сергей");
        map.put("Дмитриев", "Сергей");
        map.put("Ежов", "Илья");
        map.put("Алексеев", "Сергей");
        map.put("Зимирев", "Евгений");
        map.put("Жопов", "Сергей");

        return map;
    }

    public static void printPeopleMap(Map<String, String> map) {
        for (Map.Entry<String, String> s : map.entrySet()) {
            System.out.println(s.getKey() + " " + s.getValue());
        }
    }
}
