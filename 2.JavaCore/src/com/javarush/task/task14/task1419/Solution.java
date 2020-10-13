package com.javarush.task.task14.task1419;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Нашествие исключений
*/

public class Solution {
    public static List<Exception> exceptions = new ArrayList<Exception>();

    public static void main(String[] args) {
        initExceptions();

        for (Exception exception : exceptions) {
            System.out.println(exception);
        }
    }

    private static void initExceptions() {   //the first exception
        try {
            float i = 1 / 0;

        } catch (Exception e) {
            exceptions.add(e);
        }

        //напишите тут ваш код
        try {
            exceptions.set(exceptions.size() + 1, exceptions.get(0));
        } catch (Exception e) {
            exceptions.add(e);
        }

        try{
            Integer i = Integer.parseInt("fdash");
        } catch (Exception e) {
            exceptions.add(e);
        }

        try{
            FileInputStream reader = new FileInputStream("F:\11.jpg");
        } catch (Exception e) {
            exceptions.add(e);
        }

        try{
           int i = 1;
           if (i > 0) throw new MyException1("gasldb");
        } catch (MyException1 e) {
            exceptions.add(e);
        }

        try{
            int i = 1;
            if (i > 0) throw new MyException2("qwert");
        } catch (MyException2 e) {
            exceptions.add(e);
        }

        try{
            int i = 1;
            if (i > 0) throw new MyException3("ertyu");
        } catch (MyException3 e) {
            exceptions.add(e);
        }

        try{
            int i = 1;
            if (i > 0) throw new MyException4("yiop");
        } catch (MyException4 e) {
            exceptions.add(e);
        }

        try{
            int i = 1;
            if (i > 0) throw new MyException5("asdfdgh");
        } catch (MyException5 e) {
            exceptions.add(e);
        }

        try{
            int i = 1;
            if (i > 0) throw new MyException6("zbvnm");
        } catch (MyException6 e) {
            exceptions.add(e);
        }

    }

    private static class MyException1 extends Exception {
        public MyException1(String e) {
            super(e);
        }
    }
    private static class MyException2 extends Exception {
        public MyException2(String e) {
            super(e);
        }
    }
    private static class MyException3 extends Exception {
        public MyException3(String e) {
            super(e);
        }
    }
    private static class MyException4 extends Exception {
        public MyException4(String e) {
            super(e);
        }
    }
    private static class MyException5 extends Exception {
        public MyException5(String e) {
            super(e);
        }
    }
    private static class MyException6 extends Exception {
        public MyException6(String e) {
            super(e);
        }
    }

}
