package com.javarush.task.task29.task2909.car;

public class Truck extends Car {

    public Truck(int numberOfPassengers) {
        super(0, numberOfPassengers);
    }

    @Override
    public int getMaxSpeed() {
        final int MAX_TRUCK_SPEED = 80;
        final int MAX_SEDAN_SPEED = 120;
        final int MAX_CABRIOLET_SPEED = 90;
        return MAX_TRUCK_SPEED;
    }
}
