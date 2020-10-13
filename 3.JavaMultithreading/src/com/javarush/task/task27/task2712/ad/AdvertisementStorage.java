package com.javarush.task.task27.task2712.ad;

import java.util.LinkedList;
import java.util.List;

public class AdvertisementStorage {
    private static final AdvertisementStorage storage = new AdvertisementStorage();
    private final List<Advertisement> videos = new LinkedList<>();

    private AdvertisementStorage() {
        Object someContent = new Object();
        add(new Advertisement(someContent, "First Video", 5000, 100, 3 * 60));
        add(new Advertisement(someContent, "Second Video", 100, 10, 15 * 60));
        add(new Advertisement(someContent, "Third Video", 400, 2, 10 * 60));
//        add(new Advertisement(someContent, "Fifth Video", 640, 2, 1 * 60)); //320
//        add(new Advertisement(someContent, "First Video", 1400, 2, 3 * 60)); //700
//        add(new Advertisement(someContent, "Second Video", 800, 2, 4 * 60)); //400
//        add(new Advertisement(someContent, "Пятое видео", 900, 2, 5 * 60)); //450
//        add(new Advertisement(someContent, "Шестое видео", 640, 2, 1 * 60)); //320
//        add(new Advertisement(someContent, "Fourth Video", 760, 2, 2 * 60)); //380
    }

    public static AdvertisementStorage getInstance() {
        return storage;
    }

    public List<Advertisement> list() {
        return videos;
    }

    public void add(Advertisement advertisement) {
        videos.add(advertisement);
    }
}
