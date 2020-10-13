package com.javarush.task.task31.task3105;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* 
Добавление файла в архив
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        ZipInputStream zis = new ZipInputStream(new FileInputStream(args[1]));
        Map<ZipEntry, ByteArrayOutputStream> zipFileContent = new HashMap<>();
        ZipEntry zipEntry;
        while ((zipEntry = zis.getNextEntry()) != null) {
            if (!zipEntry.getName().split("/")[zipEntry.getName().split("/").length - 1].equals(args[0].split("/")[args[0].split("/").length - 1])) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                int count;
                byte[] buffer = new byte[1024];
                while ((count = zis.read(buffer)) != -1) {
                    baos.write(buffer, 0, count);
                }
                baos.flush();
                baos.close();
                zis.closeEntry();
                zipFileContent.put(zipEntry, baos);
            }
        }
        zis.close();

        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(args[1]));
        zos.putNextEntry(new ZipEntry("new/" + args[0].split("/")[args[0].split("/").length - 1]));
        Files.copy(Paths.get(args[0]), zos);
        zos.flush();
        zos.closeEntry();
        for (Map.Entry<ZipEntry, ByteArrayOutputStream> pair : zipFileContent.entrySet()) {
            zos.putNextEntry(new ZipEntry(pair.getKey().getName()));
            pair.getValue().writeTo(zos);
            zos.flush();
            zos.closeEntry();
        }
        zos.close();
    }
}
