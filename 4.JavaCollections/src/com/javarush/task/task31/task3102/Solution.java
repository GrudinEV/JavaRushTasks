package com.javarush.task.task31.task3102;

import java.io.File;
import java.io.IOException;
import java.util.*;

/* 
Находим все файлы
*/
public class Solution {
    public static List<String> getFileTree(String root) throws IOException {
        Queue<File> queue = new PriorityQueue<>();
        Collections.addAll(queue, new File(root).listFiles());
        List<String> listFiles = new LinkedList<>();
        while (!queue.isEmpty()) {
            File file = queue.poll();
            if (file.isDirectory()) {
                Collections.addAll(queue, file.listFiles());
            } else {
                listFiles.add(file.getAbsolutePath());
            }
        }
        return listFiles;

    }

    public static void main(String[] args) throws IOException {
        getFileTree("F:\\Distribs").forEach(System.out::println);
    }
}
