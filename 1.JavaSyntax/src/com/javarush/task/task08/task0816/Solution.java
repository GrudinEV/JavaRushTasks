package com.javarush.task.task08.task0816;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/* 
Добрая Зинаида и летние каникулы
*/

public class Solution {
    public static Map<String, Date> createMap() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("MMMMM d yyyy", Locale.ENGLISH);
        Map<String, Date> map = new HashMap<>();
        map.put("Сталлоне", dateFormat.parse("MAY 1 2012"));
        map.put("Валера", dateFormat.parse("JUNE 21 2002"));
        map.put("Игорь", dateFormat.parse("AUGUST 6 2009"));
        map.put("Шварц", dateFormat.parse("FEBRUARY 18 2014"));
        map.put("Сенька", dateFormat.parse("JULY 4 1996"));
        map.put("Чапай", dateFormat.parse("SEPTEMBER 12 1992"));
        map.put("Петька", dateFormat.parse("OCTOBER 29 2001"));
        map.put("Леонард", dateFormat.parse("JANUARY 5 2003"));
        map.put("Киса", dateFormat.parse("DECEMBER 11 2009"));
        map.put("Мюррей", dateFormat.parse("AUGUST 23 2000"));

        //напишите тут ваш код
        return map;
    }

    public static void removeAllSummerPeople(Map<String, Date> map) {
        //напишите тут ваш код
        Iterator<Map.Entry<String, Date>> iterator = map.entrySet().iterator();
        while(iterator.hasNext()) {
            Map.Entry<String, Date> pair = iterator.next();
            Date date = pair.getValue();
            if (date.getMonth() > 4 && date.getMonth() < 8) iterator.remove();
        }

    }

    public static void main(String[] args) throws ParseException {
//        Map<String, Date> map = createMap();
//        removeAllSummerPeople(map);
//        Iterator<Map.Entry<String, Date>> iterator = map.entrySet().iterator();
//        while (iterator.hasNext()) {
//            Map.Entry<String, Date> pair = iterator.next();
//            System.out.println(pair.getKey() + " " + pair.getValue());
//        }

    }
}
