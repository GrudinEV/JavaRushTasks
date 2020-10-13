package com.javarush.task.task22.task2208;

import java.util.HashMap;
import java.util.Map;

/* 
Формируем WHERE
*/
public class Solution {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put(null, "Ivanov");
        map.put("Address", null);
        System.out.println(getQuery(map));

    }

    public static String getQuery(Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        params.forEach((key, value) ->
                {
                    if (key != null && value != null) {
                        sb.append(key).append(" = '").append(value).append("' and ");
                    }
                });
        if (sb.length() > 0) sb.delete(sb.length() - 5, sb.length());
        return sb.toString();
    }
}
