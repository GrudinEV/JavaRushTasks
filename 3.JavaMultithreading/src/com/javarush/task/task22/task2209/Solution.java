package com.javarush.task.task22.task2209;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/*
Составить цепочку слов
*/
public class Solution {
    private String[] array;
    private String[] str;
    private int n = 0;

    public static void main(String[] args) throws IOException {
        //...
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader fr = new BufferedReader(new FileReader(reader.readLine()));
        reader.close();
        StringBuilder sb = new StringBuilder();
        while (fr.ready()) {
            sb.append(fr.readLine());
            sb.append(' ');
        }
        fr.close();
        sb.deleteCharAt(sb.length() - 1);
        String[] array = sb.toString().split("\\s");
        List<String> list = new ArrayList<>();
        for (String s : array) {
            if (!s.equals(""))
                list.add(s);
        }
        String[] newArray = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            newArray[i] = list.get(i);
        }
        StringBuilder result = getLine(newArray);
        System.out.print(result.toString());
    }

    private static void generateStringArray(Solution solution) {
        if (solution.n < solution.array.length) {
            for (String word : solution.array) {
                if (solution.n == solution.array.length) {
                    break;
                }
                if (solution.n == 0) {
                    solution.str[solution.n] = word;
                    solution.n++;
                    generateStringArray(solution);
                    if (solution.n < solution.array.length) {
                        solution.n--;
                        solution.str[solution.n] = "";
                    }
                } else {
                    String s = "";
                    for (String s1 : solution.str) {
                        s += s1;
                    }
                    int k = 0;
                    for  (String w : solution.array) if (w.equals(word)) k++;
                    int m = 0;
                    for  (String w : solution.str) if (w.equals(word)) m++;
                    String upperWord = solution.str[solution.n - 1].substring(solution.str[solution.n - 1].length() - 1).toUpperCase();
                    String lowerWord = solution.str[solution.n - 1].substring(solution.str[solution.n - 1].length() - 1).toLowerCase();
                    if (m < k && solution.n > 0 && (word.startsWith(upperWord) || word.startsWith(lowerWord))) {
                        solution.str[solution.n] = word;
                        solution.n++;
                        generateStringArray(solution);
                        if (solution.n < solution.array.length) {
                            solution.n--;
                            solution.str[solution.n] = "";
                        }
                    }
                }
            }
        }
    }

    public static StringBuilder getLine(String... words) {
        Solution solution = new Solution();
        solution.array = words;
        solution.str = new String[words.length];
        for (int i = 0; i < solution.str.length; i++) {
            solution.str[i] = "";
        }
        int k = 0;
        for (String word : solution.array) {
            if (word.length() > 0)
                k++;
        }
        if (k > 0) {
            generateStringArray(solution);
            StringBuilder sb = new StringBuilder("");
            for (String s : solution.str) {
                for (String word : words) {
                    if (word.equals(s)) {
                        sb.append(word);
                        break;
                    }
                }
                sb.append(" ");
            }
            sb.deleteCharAt(sb.length() - 1);
            return sb;
        } else {
            return new StringBuilder("");
        }
    }
}
