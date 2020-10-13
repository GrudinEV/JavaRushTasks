package com.javarush.task.task08.task0812;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Cамая длинная последовательность
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        //напишите тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) list.add(Integer.parseInt(reader.readLine()));
        int countMax = 1, count = 1;
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).equals(list.get(i-1))) count++;
            else {
                countMax = countMax < count ? count : countMax;
                count = 1;
            }

        }
        countMax = countMax < count ? count : countMax;
        System.out.println(countMax);

    }
}