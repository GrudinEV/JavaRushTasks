package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ConsoleHelper {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws IOException {
        return reader.readLine();
    }

    public static List<Dish> getAllDishesForOrder() throws IOException {
        List<Dish> listDishes = new ArrayList<>();
        writeMessage(Dish.allDishesToString());
        writeMessage("Будьте так любезны. Введите название выбранного для заказа блюда.");
        String dish;
        while (!(dish = readString()).equals("exit")) {
            if (Arrays.stream(Dish.values()).map(Enum::toString).collect(Collectors.toList()).contains(dish)) {
                listDishes.add(Dish.valueOf(dish));
                writeMessage("Желаете что-то ещё?");
            } else {
                writeMessage("К сожалению выбранного блюда нет в меню. Прошу повторить Ваш выбор.");
            }
        }
        return listDishes;
    }
}
