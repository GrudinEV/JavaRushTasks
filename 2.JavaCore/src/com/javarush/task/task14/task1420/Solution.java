package com.javarush.task.task14.task1420;

/* 
НОД
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception {
        int m = 0, n = 0;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        try {
            n = Integer.parseInt(reader.readLine());
            if (n <= 0) throw new Exception();
            m = Integer.parseInt(reader.readLine());
            if (m <= 0) throw new Exception();
            int min = m < n ? m : n;
            int nod = 1;
            for (int i = min; i > 1; i--) {
                if (m % i == 0 && n % i == 0) { nod = i; break; }
            }
            System.out.println(nod);
//        }
//        catch (NumberFormatException e) {
//            System.out.println("Вы ввели не число или не целое число!");
//        }
//        catch (NumNotPositiveException e) {}
        reader.close();
    }

//    private static class NumNotPositiveException extends Exception {
//        private NumNotPositiveException() {
//            System.out.println("Введённое число не положительное!");
//        }
//    }
}
