package com.javarush.task.task26.task2603;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
Убежденному убеждать других не трудно
*/
public class Solution {

    public static class CustomizedComparator<T> implements Comparator<T> {
        private Comparator<T>[] comparators;
        public CustomizedComparator(Comparator<T>... vararg) {
            this.comparators = vararg;
        }

        @Override
        public int compare(T o1, T o2) {
            int i = 0;
            while (i < comparators.length) {
                int result = comparators[i].compare(o1, o2);
                if (result == 0) {
                    i++;
                } else {
                    return result;
                }
            }
            return 0;
        }
    }

    public static class TestClass {
        public String x;
        public String y;
        public String z;
        public TestClass(String x, String y, String z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
        public void consoleout(){
            System.out.println(x+" "+y+" "+z);

        }
    }

    public static void main(String[] args) {
        TestClass first = new TestClass("aaa","bbb","aaa");
        TestClass second = new TestClass("aaa","bbb","ccc");
        TestClass third = new TestClass("aaa","ccc","aaa");
        ArrayList<TestClass> list=new ArrayList<>();
        list.add(first);list.add(second);list.add(third);

        Collections.sort(list,new CustomizedComparator<TestClass>(new Comparator<TestClass>() {
            @Override
            public int compare(TestClass o1, TestClass o2) {
                return o1.z.compareTo(o2.z);
            }
        }, new Comparator<TestClass>() {
            @Override
            public int compare(TestClass o1, TestClass o2) {
                return o1.y.compareTo(o2.y);
            }
        }, new Comparator<TestClass>() {
            @Override
            public int compare(TestClass o1, TestClass o2) {
                return o1.x.compareTo(o2.x);
            }
        }));

        for (TestClass test:list) {
            test.consoleout();
        }
    }

}
