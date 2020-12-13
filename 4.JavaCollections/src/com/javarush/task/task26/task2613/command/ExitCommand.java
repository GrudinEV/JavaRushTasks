package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

class ExitCommand implements Command{
    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage("Can you exit? (y/n)");
        String answerExit = ConsoleHelper.readString();
        if (answerExit.toLowerCase().equals("y")) {
            ConsoleHelper.writeMessage("Good bye!!!");
        }
    }
}
