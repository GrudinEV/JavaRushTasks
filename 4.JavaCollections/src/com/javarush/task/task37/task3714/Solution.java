package com.javarush.task.task37.task3714;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/* 
Древний Рим
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Input a roman number to be converted to decimal: ");
        String romanString = bufferedReader.readLine();
        System.out.println("Conversion result equals " + romanToInteger(romanString));
    }

    public static int romanToInteger(String s) {
        int n = 0;
        final char[] array = s.toCharArray();
        HashMap<Character, Integer> tableOfCorrespondence = new HashMap<>();
        tableOfCorrespondence.put('I', 1);
        tableOfCorrespondence.put('V', 5);
        tableOfCorrespondence.put('X', 10);
        tableOfCorrespondence.put('L', 50);
        tableOfCorrespondence.put('C', 100);
        tableOfCorrespondence.put('D', 500);
        tableOfCorrespondence.put('M', 1000);
        for (int i = 0; i < array.length - 1; i++) {
            int iIndex = tableOfCorrespondence.get(array[i]);
            int iNextIndex = tableOfCorrespondence.get(array[i + 1]);
            n += iNextIndex > iIndex ? - iIndex : iIndex;
        }
        n += tableOfCorrespondence.get(array[array.length - 1]);
        return n;
    }
}
