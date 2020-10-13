package com.javarush.task.task31.task3101;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/*
Проход по дереву файлов
*/
public class Solution {
    private List<File> listFiles = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        Solution solution = new Solution();
        File fileSource = new File(args[1]);
        File fileDestination = new File(fileSource.getParent() + "/allFilesContent.txt");
        FileUtils.renameFile(fileSource, fileDestination);
        FileOutputStream fileWriter = new FileOutputStream(fileDestination, true);
        File dir = new File(args[0]);
        if (dir.isDirectory()) {
            solution.walkDir(dir);
        }
        solution.listFiles.sort(Comparator.comparing(File::getName));
        solution.listFiles.forEach(x -> {
            try (FileInputStream fileReader = new FileInputStream(x)) {
                while (fileReader.available() > 0) {
                    fileWriter.write(fileReader.read());
                }
                fileWriter.write('\n');
                fileWriter.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        fileWriter.close();
//        if (dir.isDirectory()) {
//            Files.walk(Paths.get(dir.getAbsolutePath()))
//                    .map(Path::toFile)
//                    .filter(File::isFile)
//                    .filter(x -> x.length() <= 50)
//                    .filter(x -> x.getName().endsWith(".txt"))
//                    .sorted(Comparator.comparing(File::getName))
//                    .forEach(x -> {
//                        try (FileReader fileReader = new FileReader(x)) {
//                            while (fileReader.ready()) {
//                                fileWriter.write(fileReader.read());
//                            }
//                            fileWriter.write("\n");
//                            fileWriter.flush();
//                        } catch (FileNotFoundException e) {
//                            e.printStackTrace();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    });
//            fileWriter.close();
//        }
    }

    private void walkDir(File file) {
        List<File> listFiles;
        listFiles = Arrays.asList(file.listFiles());
        for (File fileFromList : listFiles) {
            if (fileFromList.isDirectory()) {
                walkDir(fileFromList);
            } else {
                if (fileFromList.isFile() && fileFromList.length() <= 50 && fileFromList.getName().endsWith(".txt")) {
                    this.listFiles.add(fileFromList);
                }
            }
        }

    }
}
