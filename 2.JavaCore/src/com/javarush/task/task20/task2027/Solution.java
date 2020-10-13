package com.javarush.task.task20.task2027;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/* 
Кроссворд
*/
public class Solution {
    public static void main(String[] args) {
        int[][] crossword = new int[][]{
                {'f', 'd', 'e', 'r', 'l', 'k'},
                {'u', 's', 'a', 'm', 'e', 'o'},
                {'l', 'n', 'g', 'r', 'o', 'v'},
                {'m', 'l', 'p', 'r', 'r', 'h'},
                {'p', 'o', 'e', 'e', 'j', 'j'}
        };
        List<Word> list = detectAllWords(crossword, "home", "same", "epga");
        for (Word word : list) {
            System.out.println(word);
        }
        /*
Ожидаемый результат
home - (5, 3) - (2, 0)
same - (1, 1) - (4, 1)
         */
    }

    public static List<Word> detectAllWords(int[][] crossword, String... words) {
        List<Word> list = new LinkedList<>();
        for (String word : words) {
            int len = word.length();
            int crossWidth = crossword.length;
            int crossLength = crossword[0].length;
            boolean wordFound = false;
            for (int i = 0; i < crossWidth; i++) {
                for (int j = 0; j < crossLength; j++) {
                    if (word.startsWith((char) crossword[i][j] + "")) {
                        if (j + len - 1 < crossLength) {
                            String w = "";
                            for (int k = 0; k < len; k++) {
                                w += (char) crossword[i][j + k];
                            }
                            if (w.equals(word)) {
                                Word newWord = new Word(word);
                                newWord.setStartPoint(j, i);
                                newWord.setEndPoint(j + len - 1, i);
                                list.add(newWord);
                                wordFound = true;
                            }
                        }
                        if (i + len - 1 < crossWidth && j + len - 1 < crossLength) {
                            String w = "";
                            for (int k = 0; k < len; k++) {
                                w += (char) crossword[i + k][j + k];
                            }
                            if (w.equals(word)) {
                                Word newWord = new Word(word);
                                newWord.setStartPoint(j, i);
                                newWord.setEndPoint(j + len - 1, i + len - 1);
                                list.add(newWord);
                                wordFound = true;
                            }
                        }
                        if (i + len - 1 < crossWidth) {
                            String w = "";
                            for (int k = 0; k < len; k++) {
                                w += (char) crossword[i + k][j];
                            }
                            if (w.equals(word)) {
                                Word newWord = new Word(word);
                                newWord.setStartPoint(j, i);
                                newWord.setEndPoint(j, i + len - 1);
                                list.add(newWord);
                                wordFound = true;
                            }
                        }
                        if (i + len - 1 < crossWidth && j > len - 2) {
                            String w = "";
                            for (int k = 0; k < len; k++) {
                                w += (char) crossword[i + k][j - k];
                            }
                            if (w.equals(word)) {
                                Word newWord = new Word(word);
                                newWord.setStartPoint(j, i);
                                newWord.setEndPoint(j - len + 1, i + len - 1);
                                list.add(newWord);
                                wordFound = true;
                            }
                        }
                        if (j > len - 2) {
                            String w = "";
                            for (int k = 0; k < len; k++) {
                                w += (char) crossword[i][j - k];
                            }
                            if (w.equals(word)) {
                                Word newWord = new Word(word);
                                newWord.setStartPoint(j, i);
                                newWord.setEndPoint(j - len + 1, i);
                                list.add(newWord);
                                wordFound = true;
                            }
                        }
                        if (i > len - 2 && j > len - 2) {
                            String w = "";
                            for (int k = 0; k < len; k++) {
                                w += (char) crossword[i - k][j - k];
                            }
                            if (w.equals(word)) {
                                Word newWord = new Word(word);
                                newWord.setStartPoint(j, i);
                                newWord.setEndPoint(j - len + 1, i - len + 1);
                                list.add(newWord);
                                wordFound = true;
                            }
                        }
                        if (i > len - 2) {
                            String w = "";
                            for (int k = 0; k < len; k++) {
                                w += (char) crossword[i - k][j];
                            }
                            if (w.equals(word)) {
                                Word newWord = new Word(word);
                                newWord.setStartPoint(j, i);
                                newWord.setEndPoint(j, i - len + 1);
                                list.add(newWord);
                                wordFound = true;
                            }
                        }
                        if (i > len - 2 && j + len - 1 < crossLength) {
                            String w = "";
                            for (int k = 0; k < len; k++) {
                                w += (char) crossword[i - k][j + k];
                            }
                            if (w.equals(word)) {
                                Word newWord = new Word(word);
                                newWord.setStartPoint(j, i);
                                newWord.setEndPoint(j + len - 1, i - len + 1);
                                list.add(newWord);
                                wordFound = true;
                            }
                        }
                    }
                    if (wordFound) break;
                }
                if (wordFound) break;
            }
        }

        return list;
    }

    public static class Word {
        private String text;
        private int startX;
        private int startY;
        private int endX;
        private int endY;

        public Word(String text) {
            this.text = text;
        }

        public void setStartPoint(int i, int j) {
            startX = i;
            startY = j;
        }

        public void setEndPoint(int i, int j) {
            endX = i;
            endY = j;
        }

        @Override
        public String toString() {
            return String.format("%s - (%d, %d) - (%d, %d)", text, startX, startY, endX, endY);
        }
    }
}
