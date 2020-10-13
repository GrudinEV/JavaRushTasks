package com.javarush.task.task22.task2207;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

/* 
Обращенные слова
*/
public class Solution {
    public static List<Pair> result = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader fileReader = new BufferedReader(new FileReader(reader.readLine()));
        reader.close();
        StringBuilder sb = new StringBuilder();
        while (fileReader.ready()) {
            sb.append(fileReader.readLine());
            sb.append(' ');
        }
        sb.deleteCharAt(sb.length() - 1);
        String[] array = sb.toString().split(" ");
        sb = new StringBuilder(" ").append(sb).append(" ");
        for (String word : array) {
            sb.delete(0, word.length() + 1);
            if (sb.indexOf(" " + new StringBuilder(word).reverse() + " ") >= 0) {
                Pair pair1 = new Pair();
                pair1.first = word;
                pair1.second = new StringBuilder(word).reverse().toString();
                Pair pair2 = new Pair();
                pair2.first = pair1.second;
                pair2.second = pair1.first;
                if (!result.contains(pair1) && !result.contains(pair2))
                    result.add(pair1);
            }
        }
        result.forEach(value ->
                System.out.println(value.first + " " + value.second));
    }

    public static class Pair {
        String first;
        String second;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (first != null ? !first.equals(pair.first) : pair.first != null) return false;
            return second != null ? second.equals(pair.second) : pair.second == null;

        }

        @Override
        public int hashCode() {
            int result = first != null ? first.hashCode() : 0;
            result = 31 * result + (second != null ? second.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return first == null && second == null ? "" :
                    first == null ? second :
                            second == null ? first :
                                    first.compareTo(second) < 0 ? first + " " + second : second + " " + first;

        }
    }

}
