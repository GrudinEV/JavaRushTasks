package com.javarush.task.task13.task1318;

/* 
Чтение файла
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        // напишите тут ваш код
        InputStreamReader inputStream = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(inputStream);
        String fileName = reader.readLine(); //считываем имя файла с клавиатуры

        FileInputStream fileInputStream = new FileInputStream(fileName);
        BufferedInputStream readFile = new BufferedInputStream(fileInputStream); //читаем в буфер данные файла

        while (readFile.available() > 0){
            char c = (char) readFile.read();
            System.out.print(c);}
        reader.close();;
        readFile.close();
    }
}