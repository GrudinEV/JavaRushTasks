package com.javarush.task.task07.task0716;

import java.util.ArrayList;

/* 
Р или Л
*/

public class Solution {
    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<String>();
        strings.add("роза");
        strings.add("лоза");
        strings.add("лира");
        strings = fix(strings);

        for (String string : strings) {
            System.out.println(string);
        }
    }

    public static ArrayList<String> fix(ArrayList<String> strings) {
        //напишите тут ваш код
        for (int i = 0; i < strings.size();) {
            String str = strings.get(i);
            char[] tray = str.toCharArray();
            int l = 0, r = 0;
            for (int j = 0; j < tray.length; j++) {
                String simbol = Character.toString(tray[j]);
                if (simbol.equals("л")) l++;
                if (simbol.equals("р")) r++;
            }
            if (l > 0 && r == 0) {
                strings.add(i, strings.get(i)); i += 2;
            }
            else
                if (r > 0 && l == 0) strings.remove(i); else i++;

        }
        return strings;
    }
}