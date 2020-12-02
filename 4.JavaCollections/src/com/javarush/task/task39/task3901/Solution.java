package com.javarush.task.task39.task3901;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/* 
Уникальные подстроки
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please enter your string: ");
        String s = bufferedReader.readLine();

        System.out.println("The longest unique substring length is: \n" + lengthOfLongestUniqueSubstring(s));
    }

    public static int lengthOfLongestUniqueSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int maxLengthString = 1;
        for (int i = 0; i < s.length() - maxLengthString; i++) {
            String subString = s.substring(i, i + 1);
            for (int j = i + 1; j < s.length(); j++) {
                String newSymbol = (j == s.length() - 1) ? s.substring(j) : s.substring(j, j + 1);
                if (!subString.contains(newSymbol)) {
                    subString += newSymbol;
                } else {
                    break;
                }
            }
            maxLengthString = Math.max(subString.length(), maxLengthString);
        }
        return maxLengthString;
    }
}
