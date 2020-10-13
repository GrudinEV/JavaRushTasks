package com.javarush.task.task22.task2201;

/* 
Строки нитей или строковые нити? Вот в чем вопрос
*/
public class Solution {
    public static void main(String[] args) {
        new Solution();
    }

    public static final String FIRST_THREAD_NAME = "1#";
    public static final String SECOND_THREAD_NAME = "2#";

    private Thread thread1;
    private Thread thread2;
    private Thread thread3;

    public Solution() {
        initThreads();
    }

    protected void initThreads() {
        this.thread1 = new Thread(new Task(this, "A\tB\tC\tD\tE\tF\tG\tH\tI"), FIRST_THREAD_NAME);
        this.thread2 = new Thread(new Task(this, "J\tK\tL\tM\tN\tO\tP\tQ\tR\tS\tT\tU\tV\tW\tX\tY\tZ"), SECOND_THREAD_NAME);
        this.thread3 = new Thread(new Task(this, "\t\t"), "3#");

        Thread.setDefaultUncaughtExceptionHandler(new OurUncaughtExceptionHandler());

        this.thread1.start();
        this.thread2.start();
        this.thread3.start();
    }

    public synchronized String getPartOfString(String string, String threadName) {
        String[] array = string.split("\t");
        StringBuilder stringBuilder = new StringBuilder();
        if (array.length < 3 || (array.length == 3 && array[1] == "")) {
           if (threadName == FIRST_THREAD_NAME)
               throw new StringForFirstThreadTooShortException();
           else if (threadName == SECOND_THREAD_NAME)
               throw new StringForSecondThreadTooShortException();
           else
               throw new RuntimeException();

        } else {
            for (int i = 1; i < array.length - 1; i++) {
                stringBuilder.append(array[i]);
                stringBuilder.append("\t");
            }
        }
        String str = stringBuilder.toString().substring(0, stringBuilder.toString().length() - 1);
        return str;
    }
}
