package com.javarush.task.task17.task1710;

import org.w3c.dom.ls.LSOutput;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* 
CRUD
*/

public class Solution {
    public static List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) {
        //start here - начни тут
        SimpleDateFormat inputDF = new SimpleDateFormat("dd/MM/yyyy");

        if (args[0].equals("-c")) {
            try {
                Date birthday = inputDF.parse(args[3]);
                allPeople.add(args[2].equals("м") ? Person.createMale(args[1], birthday) : Person.createFemale(args[1], birthday));
                System.out.println(allPeople.size() - 1);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if (args[0].equals("-u")) {
            int n = Integer.parseInt(args[1]);
            allPeople.get(n).setName(args[2]);
            allPeople.get(n).setSex(args[3].equals("м") ? Sex.MALE : Sex.FEMALE);
            try {
                allPeople.get(n).setBirthDate(inputDF.parse(args[4]));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if (args[0].equals("-d")) {
            allPeople.get(Integer.parseInt(args[1])).setName(null);
            allPeople.get(Integer.parseInt(args[1])).setSex(null);
            allPeople.get(Integer.parseInt(args[1])).setBirthDate(null);
        }

        if (args[0].equals("-i")) {
            System.out.println(allPeople.get(Integer.parseInt(args[1])));
        }
    }
}
