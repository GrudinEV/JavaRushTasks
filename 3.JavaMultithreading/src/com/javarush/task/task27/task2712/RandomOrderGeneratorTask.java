package com.javarush.task.task27.task2712;

import java.util.ArrayList;
import java.util.List;

public class RandomOrderGeneratorTask implements Runnable{
    private List<Tablet> listTablets;
    private final int ORDER_CREATING_INTERVAL;

    public RandomOrderGeneratorTask(List<Tablet> tablets, int interval) {
        this.listTablets = tablets;
        this.ORDER_CREATING_INTERVAL = interval;
    }

    @Override
    public void run() {
        boolean b = false;
        while (!b) {
            try {
                Tablet tablet = listTablets.get((int) (Math.random() * listTablets.size()));
                tablet.createTestOrder();
                Thread.sleep(ORDER_CREATING_INTERVAL);
            } catch (InterruptedException e) {
                b = true;
            }
        }
    }
}
