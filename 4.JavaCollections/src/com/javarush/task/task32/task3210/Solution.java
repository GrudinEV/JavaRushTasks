package com.javarush.task.task32.task3210;

/* 
Используем RandomAccessFile
*/

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

public class Solution {
    public static void main(String... args) throws IOException {
        RandomAccessFile raf = new RandomAccessFile(args[0], "rw");
        int startText = Integer.parseInt(args[1]);
        if (raf.length() >= startText + args[2].getBytes().length) {
            raf.seek(startText);
            byte[] array = new byte[args[2].getBytes().length];
            raf.read(array, 0, array.length);
            raf.seek(raf.length());
            if (Arrays.equals(array, args[2].getBytes())) {
                raf.write("true".getBytes());
            } else {
                raf.write("false".getBytes());
            }
        } else {
            raf.seek(raf.length());
            raf.write("false".getBytes());
        }
        raf.close();
    }
}
