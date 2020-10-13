package com.javarush.task.task08.task0823;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/* 
Омовение Рамы
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String string = reader.readLine();

        //напишите тут ваш код
        char[] strToChar = string.toCharArray();
        string = "";
        for (int i = 0; i < strToChar.length; i++)
            if ((Character.toString(strToChar[i]) != " " && (i == 0 || Character.toString(strToChar[i - 1]).equals(" "))))
                string += Character.toString(strToChar[i]).toUpperCase();
            else string += Character.toString(strToChar[i]);
        System.out.println(string);


    }
}
