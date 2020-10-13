package com.javarush.task.task22.task2212;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
Проверка номера телефона
*/
public class Solution {
    public static boolean checkTelNumber(String telNumber) {
        if (telNumber == null) return false;
        if (telNumber.matches(".*-.*\\(\\d{3}\\).*")) {
            return false;
        }
        telNumber = telNumber.replaceFirst("-", "").replaceFirst("-", "");
        if (telNumber.matches(".*\\(\\d{3}\\).*")) {
            telNumber = telNumber.replaceFirst("\\(\\d{3}\\)", "");
            return telNumber.startsWith("+") ? telNumber.matches("\\+\\d{9}") : telNumber.matches("\\d{7}");
        } else {
            return telNumber.startsWith("+") ? telNumber.matches("\\+\\d{12}") : telNumber.matches("\\d{10}");
        }
    }

    public static void main(String[] args) {
        System.out.println(checkTelNumber("+38)050(1234567"));

        Pattern p = Pattern.compile("(.(.(.)))");
        Matcher m = p.matcher("abcdef");
        while (m.find()) {
            System.out.println(m.groupCount());
            for (int i = 0; i <= m.groupCount(); i++) {
                System.out.println(i + ": " + m.group(i));
            }
        }

    }
}
