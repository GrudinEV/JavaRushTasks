package com.javarush.task.task16.task1632;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static List<Thread> threads = new ArrayList<>(5);

    static {
        threads.add(new MyThread1());
        threads.add(new MyThread2());
        threads.add(new MyThread3());
        threads.add(new MyThread4());
        threads.add(new MyThread5());

    }

    public static void main(String[] args) {
    }

    static class MyThread1 extends Thread {
        public void run() {
            while (true) {
            }
        }
    }

    static class MyThread2 extends Thread {
        public void run() {
            try {
                threads.get(0).join();
            } catch (InterruptedException e) {
                System.out.println("InterruptedException");
            }
        }

    }

    static class MyThread3 extends Thread {
        public void run() {
            while (!isInterrupted()) {
                System.out.println("Ура");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                }
            }
        }
    }

    static class MyThread4 extends Thread implements Message {
        boolean inter;

        public void showWarning() {
            inter = true;
        }

        public void run() {
            try {
               while (!inter) {
                   Thread.sleep(10);
               }
               showWarning();
            } catch (InterruptedException e) {
            }
        }
    }

    static class MyThread5 extends Thread {
        private int sum = 0;

        public void run() {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String s = "";
            while (true) {
                try {
                    s = reader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (s.equals("N") || s == null) break;
                    else sum += Integer.parseInt(s);
            }
            System.out.println(sum);
        }
    }
}