package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.command.CommandExecutor;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.*;

public class CashMachine {
    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);
        Operation operation;
        try {
            do {
                operation = ConsoleHelper.askOperation();
                CommandExecutor.execute(operation);
            } while (operation != Operation.EXIT);
        } catch (InterruptOperationException e) {
            ConsoleHelper.writeMessage("Good Bye!!!");
        }
//        List<Integer> list = Arrays.asList(500, 500, 500, 500, 500, 200, 200, 200, 200, 200, 200, 200, 200, 200,
//                100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100);
//        List<Integer> newList = CurrencyManipulator.getListBanknotes(6000, list);
//        newList.forEach(x -> System.out.print(x + " "));
    }
}
