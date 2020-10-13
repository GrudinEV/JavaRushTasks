package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.kitchen.Waiter;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;
import java.util.concurrent.LinkedBlockingQueue;

public class Restaurant {
    private final static LinkedBlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>();
    private static final int ORDER_CREATING_INTERVAL = 100;

    public static void main(String[] args) throws InterruptedException {
        List<Tablet> listTablet = new ArrayList<>();
        Cook cook1 = new Cook("Amigo");
        cook1.setQueue(orderQueue);
        Cook cook2 = new Cook("Gudvin");
        cook2.setQueue(orderQueue);
        for (int i = 1; i <= 5; i++) {
            Tablet tablet = new Tablet(i);
            tablet.setQueue(orderQueue);
            listTablet.add(tablet);
        }
        Thread cook1Thread = new Thread(cook1);
        cook1Thread.setDaemon(true);
        cook1Thread.start();
        Thread cook2Thread = new Thread(cook2);
        cook2Thread.setDaemon(true);
        cook2Thread.start();
        Thread thread = new Thread(new RandomOrderGeneratorTask(listTablet, ORDER_CREATING_INTERVAL));
        thread.start();
        Observer waiter = new Waiter();
        cook1.addObserver(waiter);
        cook2.addObserver(waiter);
        Thread.sleep(1000);
        thread.interrupt();
//        DirectorTablet directorTablet = new DirectorTablet();
//        tablet.createOrder();
//        tablet.createOrder();
//        tablet.createOrder();
//        tablet.createOrder();
//        directorTablet.printAdvertisementProfit();
//        directorTablet.printCookWorkloading();
//        directorTablet.printActiveVideoSet();
//        directorTablet.printArchivedVideoSet();
    }
}
