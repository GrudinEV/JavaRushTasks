package com.javarush.task.task21.task2113;

import java.util.ArrayList;
import java.util.List;

public class Hippodrome {
    static Hippodrome game;
    private List<Horse> horses;

    public Hippodrome(List<Horse> list) {
        this.horses = list;
    }

    public List<Horse> getHorses() {
        return horses;
    }

    public static void main (String[] args) throws InterruptedException {
        List<Horse> horses = new ArrayList<>();
        horses.add(new Horse("Privet", 3, 0));
        horses.add(new Horse("Dante", 3, 0));
        horses.add(new Horse("Valenok", 3, 0));
        game = new Hippodrome(horses);
        game.run();
        game.printWinner();
    }

    void run() throws InterruptedException {
        for (int i = 1; i <= 100; i++) {
            move();
            print();
            Thread.sleep(200);
        }
    }

    void move() {
        getHorses().forEach((value) ->
                value.move());
    }

    void print() {
        getHorses().forEach((value) ->
                value.print());
        for (int i = 0; i < 10; i++) {
            System.out.println("");
        }
    }

    public Horse getWinner() {
        double maxDistance = 0d;
        for (Horse horse : horses) {
            if (horse.getDistance() > maxDistance)
                maxDistance = horse.getDistance();
        }
        for (Horse horse : horses) {
            if (maxDistance == horse.getDistance()) return horse;
        }
        return null;
    }

    public void printWinner() {
        System.out.println("Winner is " + getWinner().getName() + "!");
    }
}
