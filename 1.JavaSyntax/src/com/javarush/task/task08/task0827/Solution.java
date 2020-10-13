package com.javarush.task.task08.task0827;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/* 
Работа с датой
*/

public class Solution {
    public static void main(String[] args) throws ParseException {
        System.out.println(isDateOdd("FEBRUARY 1 2013"));
    }

    public static boolean isDateOdd(String date) throws ParseException {

        SimpleDateFormat df = new SimpleDateFormat("MMMM d yyyy", Locale.ENGLISH);
//        Date data = new Date();
//        data = df.parse(date);
        Calendar data = new GregorianCalendar();
        data.setTime(df.parse(date));
        int day = data.get(Calendar.DAY_OF_YEAR);
        return day % 2 == 1;
    }
}
