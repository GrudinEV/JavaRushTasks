package com.javarush.task.task36.task3605;

import java.io.FileReader;
import java.io.IOException;
import java.util.TreeSet;

/* 
Использование TreeSet
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        TreeSet<Character> treeSet = new TreeSet<>();
        try (FileReader fileReader = new FileReader(args[0])){
            int character;
            while ((character = fileReader.read()) != -1 ) {
                character = character > 64 && character < 91 ? character + 32 : character;
                if (character > 96 && character < 123) {
                    treeSet.add((char) character);
                }
            }
        }
        int i = 0;
        for (Character character : treeSet) {
            System.out.print(character);
            i++;
            if (i == 5) {
                break;
            }
        }
    }
}
