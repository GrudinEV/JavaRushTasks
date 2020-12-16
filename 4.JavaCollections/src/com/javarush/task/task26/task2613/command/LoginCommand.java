package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.ResourceBundle;
import java.util.regex.Pattern;

class LoginCommand implements Command {
    private ResourceBundle validCreditCards = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "verifiedCards");
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "login");

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        ConsoleHelper.writeMessage(res.getString("specify.data"));
        while (true) {
            String numberCard, pinCode;
            while (true) {
                numberCard = ConsoleHelper.readString();
                pinCode = ConsoleHelper.readString();
                if (Pattern.matches("\\d{12}", numberCard) && Pattern.matches("\\d{4}", pinCode)) {
                    break;
                } else {
                    ConsoleHelper.writeMessage(res.getString("try.again.with.details"));
                }
            }
            if (validCreditCards.containsKey(numberCard) && (validCreditCards.getString(numberCard)).equals(pinCode)) {
                ConsoleHelper.writeMessage(String.format(res.getString("success.format"), numberCard));
                break;
            } else {
                ConsoleHelper.writeMessage(String.format(res.getString("not.verified.format"), numberCard));
                ConsoleHelper.writeMessage(res.getString("try.again.or.exit"));
            }
        }
    }
}
