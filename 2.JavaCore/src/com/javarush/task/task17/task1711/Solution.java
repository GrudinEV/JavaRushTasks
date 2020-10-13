package com.javarush.task.task17.task1711;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* 
CRUD 2
*/

public class Solution {
    public static volatile List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) {
        //start here - начни тут
        SimpleDateFormat inputDF = new SimpleDateFormat("dd/MM/yyyy");

        switch (args[0]) {
            case "-c" :
                synchronized (allPeople) {
                    for (int i = 0; i < (args.length - 1) / 3; i++) {
                        Date birthday = null;
                        try {
                            birthday = inputDF.parse(args[i * 3 + 3]);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        allPeople.add(args[i * 3 + 2].equals("м") ? Person.createMale(args[i * 3 + 1], birthday) : Person.createFemale(args[i * 3 + 1], birthday));
                        System.out.println(allPeople.size() - 1);
                    }
                    break;
                }
            case "-u" :
                synchronized (allPeople) {
                    for (int i = 0; i < (args.length - 1) / 4; i++) {
                        int n = Integer.parseInt(args[i * 4 + 1]);
                        allPeople.get(n).setName(args[i * 4 + 2]);
                        allPeople.get(n).setSex(args[i * 4 + 3].equals("м") ? Sex.MALE : Sex.FEMALE);
                        try {
                            allPeople.get(n).setBirthDate(inputDF.parse(args[i * 4 + 4]));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                }
            case "-d" :
                synchronized (allPeople) {
                    for (int i = 0; i < args.length - 1; i++) {
                        allPeople.get(Integer.parseInt(args[i + 1])).setName(null);
                        allPeople.get(Integer.parseInt(args[i + 1])).setSex(null);
                        allPeople.get(Integer.parseInt(args[i + 1])).setBirthDate(null);
                    }
                    break;
                }
            case "-i" :
                synchronized (allPeople) {
                    for (int i = 0; i < args.length - 1; i++) {
                        System.out.println(allPeople.get(Integer.parseInt(args[i + 1])));
                    }
                    break;
                }
        }

//        for (Person p : allPeople) System.out.println(p);

    }

}
