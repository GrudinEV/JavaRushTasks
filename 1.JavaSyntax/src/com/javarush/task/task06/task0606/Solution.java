package com.javarush.task.task06.task0606;

import java.io.*;

/* 
Чётные и нечётные циферки
*/

public class Solution {

    public static int even;
    public static int odd;

    public static void main(String[] args) throws IOException {
        //напишите тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int number = Integer.parseInt(reader.readLine());
        while (true) {
            if (number > 0) {
                int r = number % 2 == 0 ? even++ : odd++;
                number /= 10;
            }
            else break;
        }
        System.out.println("Even: " + even + " Odd: " + odd);
    }
}
