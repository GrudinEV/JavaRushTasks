package com.javarush.task.task19.task1918;

/* 
Знакомство с тегами
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader fileReader = new BufferedReader(new FileReader(reader.readLine()));
        reader.close();

        String str = "";
        while (fileReader.ready()) {
            str += fileReader.readLine();
        }
        fileReader.close();

        for (int i = 0; i < str.length()- 1 - args[0].length(); i++) {
            int countTag = 0;
            if (str.substring(i, i + args[0].length() + 1).equals("<" + args[0])) {
                countTag++;
                for (int j = i + args[0].length() + 2; j < str.length() - 2 - args[0].length(); j++) {
                    if (str.substring(j, j + args[0].length() + 1).equals("<" + args[0])) {
                        countTag++;
                    }
                    if (str.substring(j, j + args[0].length() + 3).equals("</" + args[0] + ">")) {
                        countTag--;
                        if (countTag == 0) {
                            System.out.println(str.substring(i, j + args[0].length() + 3));
                            break;
                        }
                    }
                }
            }
        }
    }
}
