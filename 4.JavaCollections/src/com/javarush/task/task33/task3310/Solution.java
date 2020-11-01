package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.FileBucket;
import com.javarush.task.task33.task3310.strategy.HashMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.OurHashMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.StorageStrategy;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    public static Set<Long> getIds(Shortener shortener, Set<String> strings) {
        Set<Long> setIds = new HashSet<>();
        for (String string : strings) {
            setIds.add(shortener.getId(string));
        }
        return setIds;
    }

    public static Set<String> getStrings(Shortener shortener, Set<Long> keys) {
        Set<String> setStrings = new HashSet<>();
        for (Long id : keys) {
            setStrings.add(shortener.getString(id));
        }
        return setStrings;
    }

    public static void testStrategy(StorageStrategy strategy, long elementsNumber) {
        Helper.printMessage(strategy.getClass().getSimpleName());
        Set<String> setGeneratingStrings = new HashSet<>();
        for (int i = 0; i < elementsNumber; i++) {
            String generatedString = Helper.generateRandomString();
            setGeneratingStrings.add(generatedString);
        }

        Shortener shortener = new Shortener(strategy);

        Long timeStart = new Date().getTime();
        Set<Long> setIds = getIds(shortener, setGeneratingStrings);
        Long timeEnd = new Date().getTime();
        Long timeGettingIds = timeEnd - timeStart;
        Helper.printMessage(timeGettingIds + "");

        timeStart = new Date().getTime();
        Set<String> setGettingStrings =getStrings(shortener, setIds);
        timeEnd = new Date().getTime();
        Long timeGettingStrings = timeEnd - timeStart;
        Helper.printMessage(timeGettingStrings + "");

        if (setGettingStrings.equals(setGeneratingStrings)) {
            Helper.printMessage("Тест пройден.");
        } else {
            Helper.printMessage("Тест не пройден.");
        }
    }

    public static void main(String[] args) {
//        System.gc();
//        testStrategy(new HashMapStorageStrategy(), 10000);
//        testStrategy(new OurHashMapStorageStrategy(), 10000);
        FileBucket fileBucket = new FileBucket();
    }
}
