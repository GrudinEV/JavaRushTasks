package com.javarush.task.task32.task3204;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

/* 
Генератор паролей
*/
public class Solution {
    public static void main(String[] args) {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() {
        List<Integer> digits = new ArrayList<>();
        for (int i = 48; i <= 57; i++) {
            digits.add(i);
        }
        List<Integer> lowLetters = new ArrayList<>();
        for (int i = 97; i <= 122; i++) {
            lowLetters.add(i);
        }
        List<Integer> upperLetters = new ArrayList<>();
        for (int i = 65; i <= 90; i++) {
            upperLetters.add(i);
        }
        List<Integer> allSymbols = new ArrayList<>();
        allSymbols.addAll(digits);
        allSymbols.addAll(lowLetters);
        allSymbols.addAll(upperLetters);
        int countDigits = 0, countLowLetters = 0, countUpperLetters = 0;
        int[] password = new int[8];

        while (countDigits == 0 || countLowLetters == 0 || countUpperLetters == 0) {
            countDigits = countLowLetters = countUpperLetters = 0;
            for (int i = 0; i < 8; i++) {
                int numberSymbol = (int) (Math.random() * allSymbols.size());
                int symbol = allSymbols.get(numberSymbol);
                password[i] = symbol;
                if (digits.contains(symbol)) {
                    countDigits++;
                }
                if (lowLetters.contains(symbol)) {
                    countLowLetters++;
                }
                if (upperLetters.contains(symbol)) {
                    countUpperLetters++;
                }
            }
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        for (int symbol : password) {
            baos.write(symbol);
        }

        return baos;
    }
}