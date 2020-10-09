package com.javarush.task.task07.task0713;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Играем в Jолушку
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<Integer> list3 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();
        ArrayList<Integer> list32 = new ArrayList<>();
        for (int i = 0; i < 20; i++) list.add(Integer.parseInt(reader.readLine()));
        for (Integer i : list) {
            if (i % 3 == 0) list3.add(i);
            if (i % 2 == 0) list2.add(i);
            if (i % 2 != 0 && i % 3 != 0) list32.add(i);
        }
        printList(list3);
        printList(list2);
        printList(list32);

    }

    public static void printList(ArrayList<Integer> list) {
        //напишите тут ваш код
        for (Integer i : list) System.out.println(i);
    }
}
