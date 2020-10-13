package com.javarush.task.task22.task2211;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;

/* 
Смена кодировки
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        byte[] array = new byte[1000];
        FileInputStream fis = new FileInputStream(args[0]);
        FileOutputStream fos = new FileOutputStream(args[1]);
        while (fis.available() > 0) {
            fis.read(array);
            String str = new String(array, "Windows-1251");
            array = str.getBytes("UTF-8");
            fos.write(array);
        }
        fis.close();
        fos.close();
    }
}
