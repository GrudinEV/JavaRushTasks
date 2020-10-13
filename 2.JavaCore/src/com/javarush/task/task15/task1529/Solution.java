package com.javarush.task.task15.task1529;

/* 
Осваивание статического блока
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) {

    }

    static {
        //add your code here - добавьте код тут
        try {
            reset();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static CanFly result;

    public static void reset() throws Exception{
        //add your code here - добавьте код тут
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
        if (s.equals("helicopter")) result = new Helicopter();
        if (s.equals("plane")) result = new Plane(Integer.parseInt(reader.readLine()));
        reader.close();
    }
}
