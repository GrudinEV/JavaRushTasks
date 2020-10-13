package com.javarush.task.task15.task1514;

import java.util.HashMap;
import java.util.Map;

/* 
Статики-1
*/

public class Solution {
    public static Map<Double, String> labels = new HashMap<Double, String>();

    static
    {
        labels.put(43623d,"ktb");
        labels.put((double) 5769,"fnloil");
        labels.put(123d,"56");
        labels.put((double) 87,"hkltyr");
        labels.put((double) 234,"hoyuol");

    }

    public static void main(String[] args) {
        System.out.println(labels);
    }
}
