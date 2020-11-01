package com.javarush.task.task33.task3310.strategy;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileBucket {
    private Path path;

    public FileBucket() {
        try {
            path = Files.createTempFile("", "");
        } catch (IOException e) {
            e.printStackTrace();
        }
        File file = new File(String.valueOf(path));
        file.deleteOnExit();
    }

    public long getFileSize() {
        return path.toFile().length();
    }


}
