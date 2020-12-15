package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.ResourceBundle;

class DepositCommand implements Command{
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "deposit");
    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        String currencyCode = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator currencyManipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);
        String[] depositData = ConsoleHelper.getValidTwoDigits(currencyCode);
        currencyManipulator.addAmount(Integer.parseInt(depositData[0]), Integer.parseInt(depositData[1]));
        int amount = Integer.parseInt(depositData[0]) * Integer.parseInt(depositData[1]);
        ConsoleHelper.writeMessage(String.format(res.getString("success.format"), amount, currencyCode));
    }
}
