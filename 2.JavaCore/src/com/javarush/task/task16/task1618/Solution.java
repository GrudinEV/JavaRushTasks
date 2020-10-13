package com.javarush.task.task16.task1618;

/* 
Снова interrupt
*/

public class Solution {
    public static void main(String[] args) throws InterruptedException {
        //Add your code here - добавь код тут
        TestThread t = new TestThread();
        t.start();
        Thread.sleep(500);
        t.interrupt();
    }

    //Add your code below - добавь код ниже
    public static class TestThread extends Thread {

        public void run() {
            try {
                while (true)
                    Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("Thread ended!");
            }
        }
    }
}