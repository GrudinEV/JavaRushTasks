package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class ConsoleHelper {
    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));
    private static ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "common");

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws InterruptOperationException {
        String str = null;
        try {
            str = bis.readLine();
        } catch (IOException ignored) {
        }
        if (!str.toLowerCase().equals("exit")) {
            return str;
        } else {
            throw new InterruptOperationException();
        }
    }

    public static String askCurrencyCode() throws InterruptOperationException{
        writeMessage(res.getString("choose.currency.code"));
        while (true) {
            String currencyCode = readString();
            if (currencyCode.length() == 3) {
                return currencyCode.toUpperCase();
            }
            writeMessage(res.getString("invalid.data"));
        }
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException{
        writeMessage(res.getString("choose.denomination.and.count.format"));
        while (true) {
            String twoDigits = readString().trim().replaceAll("\\s+", " ");
            if (Pattern.matches("\\d+\\s\\d+", twoDigits)) {
                return twoDigits.split("\\s");
            }
            writeMessage(res.getString("invalid.data"));
        }
    }

    public static Operation askOperation() throws InterruptOperationException{
        writeMessage(res.getString("choose.operation"));
        writeMessage("1 - " + res.getString("operation.INFO"));
        writeMessage("2 - " + res.getString("operation.DEPOSIT"));
        writeMessage("3 - " + res.getString("operation.WITHDRAW"));
        writeMessage("4 - " + res.getString("operation.EXIT"));
        while (true) {
            try {
                int numberOperations = Integer.parseInt(readString());
                return Operation.getAllowableOperationByOrdinal(numberOperations);
            } catch (IllegalArgumentException e) {
                writeMessage(res.getString("invalid.data"));
            }
        }
    }

    public static void printExitMessage() {
        writeMessage(res.getString("the.end"));
    }
}
