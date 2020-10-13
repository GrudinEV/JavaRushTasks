package com.javarush.task.task19.task1919;

/* 
Считаем зарплаты
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class Solution {
    public static void main(String[] args) throws IOException {
        TreeMap<String, Double> salary = new TreeMap<>();
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        while (reader.ready()) {
            String[] arrayString = reader.readLine().split("\\s");
            if (salary.containsKey(arrayString[0])) {
                salary.put(arrayString[0], salary.get(arrayString[0]) + Double.parseDouble(arrayString[1]));
            } else {
                salary.put(arrayString[0], Double.parseDouble(arrayString[1]));
            }
        }
        reader.close();
        for (Map.Entry<String, Double> pair : salary.entrySet()) {
            System.out.println(pair.getKey() + " " + pair.getValue());
        }
    }
}
