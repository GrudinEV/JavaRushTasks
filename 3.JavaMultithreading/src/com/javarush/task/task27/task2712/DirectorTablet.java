package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.ad.Advertisement;
import com.javarush.task.task27.task2712.ad.StatisticAdvertisementManager;
import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class DirectorTablet {
    void printAdvertisementProfit() {
        Map<String, Long> mapAllVideoSelectedByEveryDay = StatisticManager.getInstance().getAllVideoSelectedByEveryDay();
        mapAllVideoSelectedByEveryDay.forEach((day, amount) -> System.out.println(day + " - " + new BigDecimal(amount).divide(new BigDecimal("100"), 2, RoundingMode.DOWN)));
        System.out.println("Total - " +
                new BigDecimal(mapAllVideoSelectedByEveryDay.values().stream().mapToLong(x -> x).sum()).divide(new BigDecimal("100"), 2, RoundingMode.DOWN));
    }

    void printCookWorkloading() {
        Map<String, Map<String, Integer>> mapAllCookingTimeByEveryDay = StatisticManager.getInstance().getAllCookingTimeByEveryDay();
        mapAllCookingTimeByEveryDay.forEach((day, timeCookingForByDay) -> {
            System.out.println(day);
            timeCookingForByDay.forEach((cookName, cookingTime) ->
                    System.out.println(cookName + " - " + cookingTime + " min"));
            System.out.println("");
        });
    }

    void printActiveVideoSet() {
        ArrayList<Advertisement> list = (ArrayList<Advertisement>) StatisticAdvertisementManager.getInstance().getActiveAdvertisementFromStorage();
        list.sort(Comparator.comparing(Advertisement::getName, String::compareToIgnoreCase));
        list.forEach(x -> System.out.println(x.getName() + " - " + x.getHits()));
    }

    void printArchivedVideoSet() {
        ArrayList<Advertisement> list = (ArrayList<Advertisement>) StatisticAdvertisementManager.getInstance().getInactiveAdvertisementFromStorage();
        list.sort(Comparator.comparing(Advertisement::getName, String::compareToIgnoreCase));
        list.forEach(x -> System.out.println(x.getName()));
    }
}
