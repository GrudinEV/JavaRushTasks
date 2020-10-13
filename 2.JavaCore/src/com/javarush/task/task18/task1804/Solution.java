package com.javarush.task.task18.task1804;

/* 
Самые редкие байты
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static void main(String[] args) throws Exception {
        HashMap<Integer, Integer> map = new HashMap<>();
        FileInputStream inputStream = new FileInputStream(new BufferedReader(new InputStreamReader(System.in)).readLine());
        int minCountByte = inputStream.available();
        while (inputStream.available() > 0) {
            int data = inputStream.read();
            if (!map.containsKey(data)) {
                map.put(data, 1);
            } else {
                int value = map.get(data);
                map.put(data, ++value);
            }
        }
        inputStream.close();
        Collection<Integer> setValues = map.values();
        for (Integer i : setValues) {
            minCountByte = i < minCountByte ? i : minCountByte;
        }
        for (Map.Entry<Integer, Integer> pair : map.entrySet()) {
            if (pair.getValue().equals(minCountByte)) System.out.print(pair.getKey() + " ");
        }
    }
}
