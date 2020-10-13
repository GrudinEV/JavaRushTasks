package com.javarush.task.task25.task2511;

import java.util.TimerTask;

/* 
Вооружаемся до зубов!
*/
public class Solution extends TimerTask {
    protected TimerTask original;
    protected final Thread.UncaughtExceptionHandler handler;

    public Solution(TimerTask original) {

        if (original == null) {
            throw new NullPointerException();
        }
        this.original = original;
        this.handler = new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
//                System.out.println(t.getName());
//                System.out.println(e.getMessage().split("\\s")[1]);
                int lengthThreadName = t.getName().length();
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < lengthThreadName; i++) {
                    sb.append("*");
                }
                String str = e.getMessage().replaceAll(t.getName(), sb.toString());
                System.out.println(str);


            }
        };    //init handler here
    }

    public void run() {
        try {
            original.run();
        } catch (Throwable cause) {
            Thread currentThread = Thread.currentThread();
            handler.uncaughtException(currentThread, new Exception("Blah " + currentThread.getName() + " blah-blah-blah", cause));
        }
    }

    public long scheduledExecutionTime() {
        return original.scheduledExecutionTime();
    }

    public boolean cancel() {
        return original.cancel();
    }

    public static void main(String[] args) {
//        new Solution(new TimerTask() {
//            @Override
//            public void run() {
//                int x = 1;
//                int y = x / (x - 1);
//                System.out.println(y);
//            }
//        }).run();
    }
}