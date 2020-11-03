package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Helper;
import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.HashBiMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.HashMapStorageStrategy;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class SpeedTest {
    public long getTimeToGetIds(Shortener shortener, Set<String> strings, Set<Long> ids) {
        Long startTest = new Date().getTime();
        for (String string : strings) {
            Long id = shortener.getId(string);
            ids.add(id);
        }
        Long endTest = new Date().getTime();
        return endTest - startTest;
    }

    public long getTimeToGetStrings(Shortener shortener, Set<Long> ids, Set<String> strings) {
        Long startTest = new Date().getTime();
        for (Long id : ids) {
            String string = shortener.getString(id);
            strings.add(string);
        }
        Long endTest = new Date().getTime();
        return endTest - startTest;
    }

    @Test
    public void testHashMapStorage() {
        Shortener shortener1 = new Shortener(new HashMapStorageStrategy());
        Shortener shortener2 = new Shortener(new HashBiMapStorageStrategy());
        Set<String> origStrings = new HashSet<>();
        for (int i = 0; i < 10000; i++) {
            String string = Helper.generateRandomString();
            origStrings.add(string);
        }
        Set<Long> idsHashMap = new HashSet<>();
        Set<Long> idsHashBiMap = new HashSet<>();
        long timeToGetIdsWithHashMap = getTimeToGetIds(shortener1, origStrings,idsHashMap);
        long timeToGetIdsWithHashBiMap = getTimeToGetIds(shortener2, origStrings,idsHashBiMap);

        Set<String> stringsHashMap = new HashSet<>();
        Set<String> stringsHashBiMap = new HashSet<>();
        long timeToGetStringsWithHashMap = getTimeToGetStrings(shortener1, idsHashMap, stringsHashMap);
        long timeToGetStringsWithHashBiMap = getTimeToGetStrings(shortener2, idsHashBiMap, stringsHashBiMap);

        Assert.assertTrue(timeToGetIdsWithHashMap > timeToGetIdsWithHashBiMap);
        Assert.assertEquals(timeToGetStringsWithHashMap, timeToGetStringsWithHashBiMap, 30f);
    }
}
