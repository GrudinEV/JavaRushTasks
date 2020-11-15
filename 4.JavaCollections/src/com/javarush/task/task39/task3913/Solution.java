package com.javarush.task.task39.task3913;

import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Solution {
    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        LogParser logParser = new LogParser(Paths.get(/*"c:/logs/"*/"F:\\Programms\\JavaRushTasks\\4.JavaCollections\\src\\com\\javarush\\task\\task39\\task3913\\logs"));
//        System.out.println(logParser.getNumberOfUniqueIPs(sdf.parse("2020"), sdf.parse("2119")));
//        System.out.println(logParser.getUniqueIPs(sdf.parse("2026"), null));
//        System.out.println(logParser.getIPsForUser("Amigo", null, null));
//        System.out.println(logParser.getIPsForEvent(Event.LOGIN, null, null));
//        System.out.println(logParser.getIPsForStatus(Status.OK, null, null));
//        System.out.println(logParser.getAllUsers());
//        System.out.println(logParser.getNumberOfUsers(sdf.parse("2020"), null));
//        System.out.println(logParser.getNumberOfUserEvents("Diego", null, null));
//        System.out.println(logParser.getUsersForIP("146.34.15.5", null, null));
//        System.out.println(logParser.getLoggedUsers(null, sdf.parse("2015")));
//        System.out.println(logParser.getSolvedTaskUsers(null, null, 18));
//        System.out.println(logParser.getDatesForUserAndEvent("Amigo", Event.LOGIN, null, null));
//        System.out.println(logParser.getDateWhenUserLoggedFirstTime("Amigo", null, null));
//        System.out.println(logParser.getAllEvents(null, null));
//        System.out.println(logParser.getAllDoneTasksAndTheirNumber(null, null));
        System.out.println(logParser.execute("get date for user = \"Amigo\""));
        System.out.println(logParser.execute("get ip"));
        System.out.println(logParser.execute("get ip for status = \"OK\" and date between \"21.12.2013 0:00:00\" and \"03.01.2014 23:59:59\""));
    }
}