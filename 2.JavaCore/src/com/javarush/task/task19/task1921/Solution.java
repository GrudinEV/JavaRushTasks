package com.javarush.task.task19.task1921;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/* 
Хуан Хуанович
*/

public class Solution {
    public static final List<Person> PEOPLE = new ArrayList<Person>();

    public static void main(String[] args) throws IOException, ParseException {
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        while (reader.ready()) {
            String str = reader.readLine();
            String[] name = str.split("\\s\\d.+\\d");
            String[] arrayString = str.split("\\s");
            String dateString = "";
            for (int i = arrayString.length - 3; i < arrayString.length; i++) {
                dateString += (arrayString[i] + " ");
            }
            SimpleDateFormat myDateFormat = new SimpleDateFormat("d M y ");
            Person man = new Person(name[0], myDateFormat.parse(dateString));
            PEOPLE.add(man);
//            System.out.println(man.getName() + " " + new SimpleDateFormat("dd MM yyyy").format(man.getBirthDate()));
        }
        reader.close();
    }
}
