package com.javarush.task.task19.task1920;

/* 
Самый богатый
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
        Double maxSalary = 0.0;
        while (reader.ready()) {
            String str = reader.readLine();
            String[] arrayPair = str.split("\\s");
            if (salary.containsKey(arrayPair[0])) {
                salary.put(arrayPair[0], salary.get(arrayPair[0]) + Double.parseDouble(arrayPair[1]));
            } else {
                salary.put(arrayPair[0], Double.parseDouble(arrayPair[1]));
            }
            maxSalary = maxSalary < salary.get(arrayPair[0]) ? salary.get(arrayPair[0]) : maxSalary;
        }
        reader.close();
        for (Map.Entry<String, Double> pair : salary.entrySet()) {
            if (pair.getValue().equals(maxSalary))
                System.out.println(pair.getKey());
        }
    }
}
