package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;

import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;

class InfoCommand implements Command{
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "info");

    @Override
    public void execute() {
        ConsoleHelper.writeMessage(res.getString("before"));
        if (CurrencyManipulatorFactory.getAllCurrencyManipulators().isEmpty()) {
            ConsoleHelper.writeMessage(res.getString("no.money"));
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
            ConsoleHelper.writeMessage(res.getString("no.money"));
        }
    }
}
