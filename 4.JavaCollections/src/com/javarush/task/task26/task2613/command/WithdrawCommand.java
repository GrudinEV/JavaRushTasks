package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;
import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

class WithdrawCommand implements Command{
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "withdraw");
    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        String currencyCode = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator currencyManipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);
        ConsoleHelper.writeMessage(res.getString("specify.amount"));
        String amount;
        while (true) {
            while (true) {
                amount = ConsoleHelper.readString();
                if (Pattern.matches("\\d+", amount)) {
                    break;
                }
                ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
            }
            if (currencyManipulator.isAmountAvailable(Integer.parseInt(amount))) {
                try {
                    Map<Integer, Integer> withdrawMap = currencyManipulator.withdrawAmount(Integer.parseInt(amount));
                    for (Map.Entry<Integer, Integer> entry : withdrawMap.entrySet()) {
                        ConsoleHelper.writeMessage("\t" + entry.getKey() + " - " + entry.getValue());
                    }
                    ConsoleHelper.writeMessage(String.format(res.getString("success.format"), Integer.parseInt(amount), currencyCode));
                    break;
                } catch (NotEnoughMoneyException e) {
                    ConsoleHelper.writeMessage(res.getString("exact.amount.not.available"));
                }
            } else {
                ConsoleHelper.writeMessage(res.getString("not.enough.money"));
            }
        }

    }
}
