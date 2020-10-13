package com.javarush.task.task25.task2506;

public class LoggingStateThread extends Thread {
    private Thread thread;

    public LoggingStateThread(Thread target) {
        this.thread = target;
        setDaemon(true);
    }

    @Override
    public void run() {
        State state = thread.getState();
        System.out.println(state);
        State stateNew;
        do {
            stateNew = thread.getState();
            if (state != stateNew) {
                System.out.println(stateNew);
                state = stateNew;
            }
        } while (stateNew != State.TERMINATED);
    }
}
