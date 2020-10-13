package com.javarush.task.task26.task2601;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

/*
Почитать в инете про медиану выборки
*/
public class Solution {

    public static void main(String[] args) {
//        Integer[] array = new Integer[]{13, 8, 15, 5, 17, 33, 21 ,16};
//        Integer[] newArray = sort(array);
//        for (Integer i : newArray) {
//            System.out.print(i + " ");
//        }
    }

    public static Integer[] sort(Integer[] array) {
        //implement logic here
        Arrays.sort(array);
        int m;
        if (array.length % 2 == 1) {
            m = array[array.length / 2];
        } else {
            m = (array[array.length / 2] + array[array.length / 2 - 1]) / 2;
        }
        Comparator<Integer> sortByMediana = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                int delta1 = (o1 > m ? o1 - m : m - o1);
                int delta2 = (o2 > m ? o2 - m : m - o2);
                if (delta1 != delta2) {
                    return delta1 - delta2;
                } else {
                    return o1 - o2;
                }
            }
        };
        Arrays.sort(array, sortByMediana);
        return array;
    }
}
