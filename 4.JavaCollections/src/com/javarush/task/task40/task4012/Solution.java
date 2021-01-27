package com.javarush.task.task40.task4012;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.chrono.ChronoLocalDateTime;
import java.time.temporal.ChronoUnit;

/* 
Полезные методы DateTime API
*/

public class Solution {
    public static void main(String[] args) {
        LocalDate time1 = LocalDate.now().minusWeeks(1);
        LocalDate time2 = LocalDate.now().plusWeeks(2);
        System.out.println(getPeriodBetween(time1, time2));
    }

    public static boolean isLeap(LocalDate date) {
        if (date != null) {
            return date.isLeapYear();
        }
        return false;
    }

    public static boolean isBefore(LocalDateTime dateTime) {
        if (dateTime != null) {
            LocalDateTime now = LocalDateTime.now();
            return dateTime.isBefore(now);
        }
        return false;
    }

    public static LocalTime addTime(LocalTime time, int n, ChronoUnit chronoUnit) {
        return time.plus(n, chronoUnit);
    }

    public static Period getPeriodBetween(LocalDate firstDate, LocalDate secondDate) {
        return firstDate.isBefore(secondDate) ? Period.between(firstDate, secondDate) : Period.between(secondDate, firstDate);
    }
}
