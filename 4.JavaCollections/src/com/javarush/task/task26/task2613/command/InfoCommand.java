package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;

import java.util.concurrent.atomic.AtomicBoolean;

class InfoCommand implements Command{
    @Override
    public void execute() {
        if (CurrencyManipulatorFactory.getAllCurrencyManipulators().isEmpty()) {
            System.out.println("No money available.");
            return;
        }
        AtomicBoolean noMoney = new AtomicBoolean(true);
        CurrencyManipulatorFactory.
                getAllCurrencyManipulators().
                forEach(x -> {
                    if (x.getTotalAmount() > 0) {
                        System.out.println(x.getCurrencyCode() + " - " + x.getTotalAmount());
                        noMoney.set(false);
                    }
                });
        if (noMoney.get()) {
            System.out.println("No money available.");
        }
    }
}
