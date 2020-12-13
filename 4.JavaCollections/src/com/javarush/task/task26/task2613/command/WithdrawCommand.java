package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;
import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.Map;
import java.util.regex.Pattern;

class WithdrawCommand implements Command{
    @Override
    public void execute() throws InterruptOperationException {
        String currencyCode = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator currencyManipulator = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currencyCode);
        ConsoleHelper.writeMessage("Please enter the amount of currency you want to receive. ");
        String amount;
        while (true) {
            while (true) {
                amount = ConsoleHelper.readString();
                if (Pattern.matches("\\d+", amount)) {
                    break;
                }
                ConsoleHelper.writeMessage("You inserted incorrect data! Insert please correct the amount of currency you want to receive.");
            }
            if (currencyManipulator.isAmountAvailable(Integer.parseInt(amount))) {
                try {
                    Map<Integer, Integer> withdrawMap = currencyManipulator.withdrawAmount(Integer.parseInt(amount));
                    for (Map.Entry<Integer, Integer> entry : withdrawMap.entrySet()) {
                        System.out.println("\t" + entry.getKey() + " - " + entry.getValue());
                    }
                    System.out.println("You have successfully withdrawn the required amount.");
                    break;
                } catch (NotEnoughMoneyException e) {
                    ConsoleHelper.writeMessage("There are not enough banknotes in the ATM to withdraw this amount. Enter a new amount to withdraw.");
                }
            } else {
                ConsoleHelper.writeMessage("There is not enough money on the account. Enter a new amount to withdraw.");
            }
        }

    }
}
