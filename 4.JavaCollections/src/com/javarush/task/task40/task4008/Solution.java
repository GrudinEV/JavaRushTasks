package com.javarush.task.task40.task4008;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.regex.Pattern;

/* 
Работа с Java 8 DateTime API
*/

public class Solution {
    public static void main(String[] args) {
        printDate("21.4.2014 15:56:45");
        System.out.println();
        printDate("21.4.2014");
        System.out.println();
        printDate("17:33:40");
    }

    public static void printDate(String date) {
        //напишите тут ваш код

        if (Pattern.matches("\\d{1,2}\\.\\d{1,2}\\.\\d{4} \\d{1,2}:\\d{1,2}:\\d{1,2}", date)) {
            String[] dateArr = date.split(" ");
            DateTimeFormatter dtfDate = DateTimeFormatter.ofPattern("d.M.yyyy");
            DateTimeFormatter dtfTime = DateTimeFormatter.ofPattern("H:m:s");
            LocalDate localDate = LocalDate.parse(dateArr[0], dtfDate);
            LocalTime localTime = LocalTime.parse(dateArr[1], dtfTime);
            printDate(localDate);
            printTime(localTime);
        }

        if (Pattern.matches("\\d{1,2}\\.\\d{1,2}\\.\\d{4}", date)) {
            DateTimeFormatter dtfDate = DateTimeFormatter.ofPattern("d.M.yyyy");
            LocalDate localDate = LocalDate.parse(date, dtfDate);
            printDate(localDate);
        }

        if (Pattern.matches("\\d{1,2}:\\d{1,2}:\\d{1,2}", date)) {
            DateTimeFormatter dtfTime = DateTimeFormatter.ofPattern("H:m:s");
            LocalTime localTime = LocalTime.parse(date, dtfTime);
            printTime(localTime);
        }
    }

    private static void printTime(LocalTime localTime) {
        System.out.println("AM или PM: " + (localTime.get(ChronoField.AMPM_OF_DAY) == 1 ? "PM" : "AM"));
        System.out.println("Часы: " + localTime.get(ChronoField.HOUR_OF_AMPM));
        System.out.println("Часы дня: " + localTime.get(ChronoField.HOUR_OF_DAY));
        System.out.println("Минуты: " + localTime.getMinute());
        System.out.println("Секунды: " + localTime.getSecond());
    }

    private static void printDate(LocalDate localDate) {
        System.out.println("День: " + localDate.getDayOfMonth());
        System.out.println("День недели: " + localDate.getDayOfWeek().getValue());
        System.out.println("День месяца: " + localDate.getDayOfMonth());
        System.out.println("День года: " + localDate.getDayOfYear());
        System.out.println("Неделя месяца: " + localDate.get(ChronoField.ALIGNED_WEEK_OF_MONTH));
        System.out.println("Неделя года: " + localDate.get(ChronoField.ALIGNED_WEEK_OF_YEAR));
        System.out.println("Месяц: " + localDate.getMonthValue());
        System.out.println("Год: " + localDate.getYear());
    }
}
