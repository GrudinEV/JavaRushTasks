package com.javarush.task.task13.task1326;

/* 
Сортировка четных чисел из файла
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) throws IOException {
        // напишите тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine(); //ввод имени файла с клавиатуры

        FileInputStream fileReader = new FileInputStream(fileName);
        Scanner bufferedFileReader = new Scanner(fileReader);
        ArrayList<Integer> list = new ArrayList<>();
        while (bufferedFileReader.hasNext()) {
            Integer i = Integer.parseInt(bufferedFileReader.nextLine());
            if (i % 2 == 0) list.add(i);
        }
        Collections.sort(list);
        for(Integer i : list) System.out.println(i);

        reader.close();
        bufferedFileReader.close();

    }
}
