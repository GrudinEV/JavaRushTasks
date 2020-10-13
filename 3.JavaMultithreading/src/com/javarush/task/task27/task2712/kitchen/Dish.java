package com.javarush.task.task27.task2712.kitchen;

import java.util.Arrays;

public enum Dish {
    Fish(25), Steak(30), Soup(15), Juice(5), Water(3);
    private int duration;

    Dish(int duration) {
        this.duration = duration;
    }

    public static String allDishesToString() {
        String str = Arrays.toString(values());
        str = str.substring(1, str.length() - 1);
        return str;
    }

    public int getDuration() {
        return duration;
    }
}
