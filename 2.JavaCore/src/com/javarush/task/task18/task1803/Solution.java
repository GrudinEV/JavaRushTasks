package com.javarush.task.task18.task1803;

/* 
Самые частые байты
*/

import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static void main(String[] args) throws Exception {
        HashMap<Integer, Integer> map = new HashMap<>();
        FileInputStream inputStream = new FileInputStream(new BufferedReader(new InputStreamReader(System.in)).readLine());
        int maxCountByte = 1;
        while (inputStream.available() > 0) {
            int data = inputStream.read();
            if (!map.containsKey(data)) {
                map.put(data, 1);
            } else {
                int value = map.get(data);
                map.put(data, ++value);
                maxCountByte = maxCountByte < value ? value : maxCountByte;
            }
        }
        inputStream.close();
        for (Map.Entry<Integer, Integer> pair : map.entrySet()) {
            if (pair.getValue().equals(maxCountByte)) System.out.print(pair.getKey() + " ");
        }
    }
}
