package com.javarush.task.task27.task2712.statistic;

import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventType;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class StatisticManager {
    private static StatisticManager manager = new StatisticManager();
    private StatisticStorage statisticStorage = new StatisticStorage();

    private StatisticManager() {
    }

    public static StatisticManager getInstance() {
        return manager;
    }

    public Map<String, Long> getAllVideoSelectedByEveryDay() {
        List<EventDataRow> listAllVideosSelected = statisticStorage.getStorage().get(EventType.SELECTED_VIDEOS);
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        Map<String, Long> mapAllVideoSelectedByEveryDay = new TreeMap<>((o1, o2) -> {
                try {
                    Date o1Date = df.parse(o1);
                    Date o2Date = df.parse(o2);
                    return o2Date.compareTo(o1Date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return o2.compareTo(o1);
        });
        for (EventDataRow videoSelected : listAllVideosSelected) {
            String day = df.format(videoSelected.getDate());
            long amount = ((VideoSelectedEventDataRow) videoSelected).getAmount();
            if (mapAllVideoSelectedByEveryDay.containsKey(day)) {
                amount += mapAllVideoSelectedByEveryDay.get(day);
                mapAllVideoSelectedByEveryDay.replace(day, amount);
            } else {
                mapAllVideoSelectedByEveryDay.put(day, amount);
            }
        }
        return mapAllVideoSelectedByEveryDay;
    }

    public Map<String, Map<String, Integer>> getAllCookingTimeByEveryDay() {
        List<EventDataRow> listAllCookingTime = statisticStorage.getStorage().get(EventType.COOKED_ORDER);
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        Map<String, Map<String, Integer>> mapAllCookingTimeByEveryDay = new TreeMap<>((o1, o2) -> {
            try {
                Date o1Date = df.parse(o1);
                Date o2Date = df.parse(o2);
                return o2Date.compareTo(o1Date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return o2.compareTo(o1);
        });
        for (EventDataRow cookedOrder : listAllCookingTime) {
            String day = df.format(cookedOrder.getDate());
            String cookName = ((CookedOrderEventDataRow) cookedOrder).getCookName();
            int cookingTimeSeconds = ((CookedOrderEventDataRow) cookedOrder).getTime();
            if (mapAllCookingTimeByEveryDay.containsKey(day)) {
                Map<String, Integer> timeCookingForByDay = mapAllCookingTimeByEveryDay.get(day);
                if (mapAllCookingTimeByEveryDay.get(day).containsKey(cookName)){
                    cookingTimeSeconds += timeCookingForByDay.get(cookName);
                    timeCookingForByDay.replace(cookName, cookingTimeSeconds);
                } else {
                    timeCookingForByDay.put(cookName, cookingTimeSeconds);
                }
                mapAllCookingTimeByEveryDay.replace(day, timeCookingForByDay);
            } else {
                Map<String, Integer> timeCookingForByDay = new TreeMap<>();
                timeCookingForByDay.put(cookName, cookingTimeSeconds);
                mapAllCookingTimeByEveryDay.put(day, timeCookingForByDay);
            }
        }
        return mapAllCookingTimeByEveryDay;
    }

    public void register(EventDataRow data) {
        statisticStorage.put(data);
    }

    private class StatisticStorage {
        Map<EventType, List<EventDataRow>> storage = new HashMap<>();

        public StatisticStorage() {
            for (EventType type : EventType.values()) {
                storage.put(type, new ArrayList<EventDataRow>());
            }



        }

        public Map<EventType, List<EventDataRow>> getStorage() {
            return storage;
        }

        private void put(EventDataRow data) {
            List<EventDataRow> list = storage.get(data.getType());
            list.add(data);
            storage.replace(data.getType(), list);
        }
    }
}
