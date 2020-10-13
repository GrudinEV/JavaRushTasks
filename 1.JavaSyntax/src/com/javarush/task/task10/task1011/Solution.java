package com.javarush.task.task10.task1011;

/* 
Большая зарплата
*/

import java.util.ArrayList;
import java.util.Collections;

public class Solution {
    public static void main(String[] args) {
        String s = "Я не хочу изучать Java, я хочу большую зарплату";

        //напишите тут ваш код
        char[] array = s.toCharArray();
        ArrayList<Character> list = new ArrayList<>();
        for (int i = 0; i < array.length; i++)
        list.add(array[i]);
        for (int i = 0; i < 40; i++) {
            for (int j = 0; j < list.size(); j++) {
                System.out.print(list.get(j));
            }
            System.out.println();
            list.remove(0);

        }
    }

//    @Override
//    public String toString(ArrayList list) {
//        String s = "";
//        for (int i = 0; i < list.size(); i++) s +=list.get(i);
//        return s;
//
//    }

}

