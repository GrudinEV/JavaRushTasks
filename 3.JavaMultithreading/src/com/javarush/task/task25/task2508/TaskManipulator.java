package com.javarush.task.task25.task2508;

public class TaskManipulator implements Runnable, CustomThreadManipulator {
    private Thread thread;
    @Override
    public void run() {
        Thread current = Thread.currentThread();
//        System.out.println(current.isInterrupted());
        boolean b = false;
        while (!b) {
            //            System.out.println(current.isInterrupted());
            try {
                System.out.println(current.getName());
                Thread.sleep(100);
            } catch (InterruptedException e) {
                b = true;
            }

        }
    }

    @Override
    public void start(String threadName) {
//        if (thread != null) {
//            while (thread.getState() != Thread.State.TERMINATED) {}
//        }
        thread = new Thread(this, threadName);
        thread.start();
    }

    @Override
    public void stop() {
        thread.interrupt();
//        System.out.println(thread.isInterrupted());
    }
}
