package com.javarush.task.task39.task3906;

public class ElectricPowerSwitch {
    private Switchable switche;

    public ElectricPowerSwitch(Switchable switche) {
        this.switche = switche;
    }

    public void press() {
        System.out.println("Power switch flipped.");
        if (switche.isOn()) {
            switche.turnOff();
        } else {
            switche.turnOn();
        }
    }
}
