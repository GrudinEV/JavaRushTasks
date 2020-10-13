package com.javarush.task.task31.task3106;

import java.io.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/*
Разархивируем файл
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        List<String> list = new LinkedList<>();
        for (int i = 1; i < args.length; i++) {
            list.add(args[i]);
        }
        List<FileInputStream> listInputStream = list.stream().sorted().map(x -> {
            try {
                return new FileInputStream(x);
            } catch (FileNotFoundException e) {
            }
            return null;
        }).collect(Collectors.toList());
        ZipInputStream zis = new ZipInputStream(new SequenceInputStream(Collections.enumeration(listInputStream)));
        FileOutputStream fos = new FileOutputStream(args[0]);
        ZipEntry zipEntry = zis.getNextEntry();
        int count;
        byte[] buffer = new byte[1024];
        while ((count = zis.read(buffer)) != -1) {
            fos.write(buffer, 0, count);
        }
        fos.flush();

        zis.closeEntry();
        zis.close();
        fos.close();
    }
}
