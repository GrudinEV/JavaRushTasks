package com.javarush.task.task30.task3004;

import java.util.concurrent.RecursiveTask;

public class BinaryRepresentationTask extends RecursiveTask<String> {
    private int i;
    public BinaryRepresentationTask(int i) {
        this.i = i;
    }

    @Override
    protected String compute() {
        if (i / 2 > 0) {
            return new BinaryRepresentationTask(i / 2).fork().join() + i % 2;
        } else return String.valueOf(i % 2);
    }
}
