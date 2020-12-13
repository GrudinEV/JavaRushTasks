package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class ConsoleHelper {
    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));

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
        writeMessage("Insert please currency code.");
        while (true) {
            String currencyCode = readString();
            if (currencyCode.length() == 3) {
                return currencyCode.toUpperCase();
            }
            writeMessage("You inserted incorrect data! Insert please correct currency code.");
        }
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException{
        writeMessage("Insert please denomination and count.");
        while (true) {
            String twoDigits = readString().trim().replaceAll("\\s+", " ");
            if (Pattern.matches("\\d+\\s\\d+", twoDigits)) {
                return twoDigits.split("\\s");
            }
            writeMessage("You inserted incorrect denomination and count! Re-enter please.");
        }
    }

    public static Operation askOperation() throws InterruptOperationException{
        writeMessage("Insert please number of operation (1 - INFO, 2 - DEPOSIT, 3 - WITHDRAW, 4 - EXIT).");
        while (true) {
            try {
                int numberOperations = Integer.parseInt(readString());
                return Operation.getAllowableOperationByOrdinal(numberOperations);
            } catch (IllegalArgumentException e) {
                writeMessage("You inserted incorrect number of operation! Re-enter please(1 - INFO, 2 - DEPOSIT, 3 - WITHDRAW, 4 - EXIT).");
            }
        }
    }
}
