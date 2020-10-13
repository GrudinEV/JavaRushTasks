package com.javarush.task.task25.task2512;

import java.util.ArrayList;
import java.util.List;

/*
Живем своим умом
*/
public class Solution implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        Throwable ex = e;
        t.interrupt();
        List<Throwable> list = new ArrayList<>();
        do {
            list.add(0, ex);
            ex = ex.getCause();
        } while (ex != null);
        for (Throwable exx : list) {
            System.out.println(exx.getClass().getName() + ": " + exx.getMessage());
        }
    }

    public static void main(String[] args) {
        new Solution().uncaughtException(new Thread(), new Exception("ABC", new RuntimeException("DEF", new IllegalAccessException("GHI"))));
    }
}
