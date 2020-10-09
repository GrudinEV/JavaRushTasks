package com.javarush.task.task19.task1904;

/* 
И еще один адаптер
*/

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {

    }

    public static class PersonScannerAdapter implements PersonScanner {
        private final Scanner fileScanner;

        public PersonScannerAdapter(Scanner fileScanner) {
            this.fileScanner = fileScanner;
        }

        @Override
        public Person read() throws IOException {
            String str = fileScanner.nextLine();
            String[] arrayData = str.split(" ");
            SimpleDateFormat myDateFormat = new SimpleDateFormat("ddMMyyyy");
            try {
                return new Person(arrayData[1], arrayData[2], arrayData[0], myDateFormat.parse(arrayData[3] + arrayData[4] + arrayData[5]));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public void close() throws IOException {
            fileScanner.close();

        }
    }
}
