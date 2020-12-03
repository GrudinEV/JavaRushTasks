package com.javarush.task.task39.task3908;

/* 
Возможен ли палиндром?
*/

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static void main(String[] args) {
        System.out.println(isPalindromePermutation("qweratqwertghfgh"));
    }

    public static boolean isPalindromePermutation(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int countOddSymbolCount = 0;
        s = s.toLowerCase();
        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() % 2 == 1) {
                countOddSymbolCount++;
            }
        }
        return countOddSymbolCount <= 1;
    }
}
