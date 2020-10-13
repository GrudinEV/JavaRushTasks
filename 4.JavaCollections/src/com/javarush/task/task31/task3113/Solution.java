package com.javarush.task.task31.task3113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/* 
Что внутри папки?
*/
public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Path folderNamePath = Paths.get(reader.readLine());
        if (Files.isDirectory(folderNamePath)) {
            int countFolders;
            int countFiles;
            int fileSize;
            countFolders = (int) Files.walk(folderNamePath).filter(Files::isDirectory).count() - 1;
            countFiles = (int) Files.walk(folderNamePath).filter(Files::isRegularFile).count();
            fileSize = (int) Files.walk(folderNamePath)
                    .filter(Files::isRegularFile)
                    .mapToInt(path -> {
                        try {
                            return (int) Files.size(path);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return 0;
                    })
                    .sum();
            System.out.println("Всего папок - " + countFolders);
            System.out.println("Всего файлов - " + countFiles);
            System.out.println("Общий размер - " + fileSize);
        } else {
            System.out.println(folderNamePath + " - не папка");
        }
    }
}
