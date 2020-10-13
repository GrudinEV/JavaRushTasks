package com.javarush.task.task25.task2502;

import java.util.ArrayList;
import java.util.List;

/* 
Машину на СТО не повезем!
*/
public class Solution {
    public static enum Wheel {
        FRONT_LEFT,
        FRONT_RIGHT,
        BACK_LEFT,
        BACK_RIGHT
    }

    public static class Car {
        protected List<Wheel> wheels;

        public Car() throws Exception {
            //init wheels here
            wheels = new ArrayList<>();
            if (loadWheelNamesFromDB().length != 4) {
                throw new Exception();
            }
            for (String wheel : loadWheelNamesFromDB()) {
                if (!wheels.contains(Wheel.valueOf(wheel))) {
                    wheels.add(Wheel.valueOf(wheel));
                }
            }
            if (wheels.size() != 4) {
                throw new Exception();
            }
        }

        protected String[] loadWheelNamesFromDB() {
            //this method returns mock data
            return new String[]{"FRONT_LEFT", "FRONT_RIGHT", "BACK_LEFT", "BACK_RIGHT"};
        }
    }

    public static void main(String[] args) throws Exception {
        Car car = new Car();
        car.wheels.forEach(wheel ->
                System.out.println(wheel));
    }
}
