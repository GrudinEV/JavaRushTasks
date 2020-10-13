package com.javarush.task.task19.task1916;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Отслеживаем изменения
*/

public class Solution {
    public static List<LineItem> lines = new ArrayList<LineItem>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader fileReader1 = new BufferedReader(new FileReader(reader.readLine()));
        BufferedReader fileReader2 = new BufferedReader(new FileReader(reader.readLine()));
        reader.close();

        ArrayList<String> linesFromFile1 = new ArrayList<>();
        while (fileReader1.ready()) {
            linesFromFile1.add(fileReader1.readLine());
        }
        fileReader1.close();

        ArrayList<String> linesFromFile2 = new ArrayList<>();
        while (fileReader2.ready()) {
            linesFromFile2.add(fileReader2.readLine());
        }
        fileReader2.close();

        int i1 = 0, i2 = 0;

        while (true) {
            if (linesFromFile1.get(i1).equals(linesFromFile2.get(i2))) {
                lines.add(new LineItem(Type.SAME, linesFromFile1.get(i1)));
                i1++;
                i2++;
            } else if (!linesFromFile1.get(i1).equals(linesFromFile2.get(i2)) && linesFromFile1.get(i1 + 1).equals(linesFromFile2.get(i2))) {
                lines.add(new LineItem(Type.REMOVED, linesFromFile1.get(i1)));
                i1++;
            } else if (!linesFromFile1.get(i1).equals(linesFromFile2.get(i2)) && linesFromFile1.get(i1).equals(linesFromFile2.get(i2 + 1))) {
                lines.add(new LineItem(Type.ADDED, linesFromFile2.get(i2)));
                i2++;
            }
            if (i1 == (linesFromFile1.size() - 1) && i2 == linesFromFile2.size()) {
                lines.add(new LineItem(Type.REMOVED, linesFromFile1.get(i1)));
                break;
            }
            if (i1 == linesFromFile1.size() && i2 == (linesFromFile2.size() - 1)) {
                lines.add(new LineItem(Type.ADDED, linesFromFile2.get(i2)));
                break;
            }
            if (i1 == (linesFromFile1.size() - 1) && i2 == (linesFromFile2.size() - 1))
                break;
        }
//        for (LineItem lineItem : lines) {
//            System.out.println(lineItem.type + " " + lineItem.line);
//        }
    }


    public static enum Type {
        ADDED,        //добавлена новая строка
        REMOVED,      //удалена строка
        SAME          //без изменений
    }

    public static class LineItem {
        public Type type;
        public String line;

        public LineItem(Type type, String line) {
            this.type = type;
            this.line = line;
        }
    }
}
