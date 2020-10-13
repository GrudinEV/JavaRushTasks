package com.javarush.task.task06.task0622;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* 
Числа по возрастанию
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        //напишите тут ваш код
        int [] numbers ={0, 0, 0, 0, 0};
        for (int i = 0; i <= 4; i++)
            numbers[i] = Integer.parseInt(reader.readLine());
        for (int i = 4; i >= 1; i--) {
            int m = numbers[0], k = 0;
            for (int j=1; j <=i; j++)
                if (m < numbers[j]) { m = numbers[j]; k = j; }
            if (k != i) { numbers[k] = numbers[i]; numbers[i] = m; }
        }
        for (int i = 0; i <= 4; i++)
            System.out.println(numbers[i]);

    }
}
