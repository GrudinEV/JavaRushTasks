package com.javarush.task.task27.task2712.ad;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StatisticAdvertisementManager {
    private static StatisticAdvertisementManager statisticAdvertisementManager = new StatisticAdvertisementManager();

    private StatisticAdvertisementManager() {
    }

    public static StatisticAdvertisementManager getInstance() {
        return statisticAdvertisementManager;
    }

    public List<Advertisement> getActiveAdvertisementFromStorage() {
        return AdvertisementStorage.getInstance().list().stream()
                .filter(x -> x.getHits() > 0)
                .collect(Collectors.toList());
    }

    public List<Advertisement> getInactiveAdvertisementFromStorage() {
        return AdvertisementStorage.getInstance().list().stream()
                .filter(x -> x.getHits() == 0)
                .collect(Collectors.toList());
    }
}
