package com.javarush.task.task28.task2805;

import java.util.concurrent.atomic.AtomicInteger;

public class MyThread extends Thread {
    private final static AtomicInteger priority = new AtomicInteger(0);

    public MyThread() {
        this.setPriority(priority.getAndIncrement() % 10 + 1);
    }

    public MyThread(Runnable target) {
        super(target);
        this.setPriority(priority.getAndIncrement() % 10 + 1);
    }

    public MyThread(ThreadGroup group, Runnable target) {
        super(group, target);
        int p = priority.getAndIncrement() % 10 + 1;
        this.setPriority(Math.min(p, group.getMaxPriority()));
    }

    public MyThread(String name) {
        super(name);
        this.setPriority(priority.getAndIncrement() % 10 + 1);
    }

    public MyThread(ThreadGroup group, String name) {
        super(group, name);
        int p = priority.getAndIncrement() % 10 + 1;
        this.setPriority(Math.min(p, group.getMaxPriority()));
    }

    public MyThread(Runnable target, String name) {
        super(target, name);
        this.setPriority(priority.getAndIncrement() % 10 + 1);
    }

    public MyThread(ThreadGroup group, Runnable target, String name) {
        super(group, target, name);
        int p = priority.getAndIncrement() % 10 + 1;
        this.setPriority(Math.min(p, group.getMaxPriority()));
    }

    public MyThread(ThreadGroup group, Runnable target, String name, long stackSize) {
        super(group, target, name, stackSize);
        int p = priority.getAndIncrement() % 10 + 1;
        this.setPriority(Math.min(p, group.getMaxPriority()));
    }
}
