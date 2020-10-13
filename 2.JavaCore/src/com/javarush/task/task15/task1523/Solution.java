package com.javarush.task.task15.task1523;

/* 
Перегрузка конструкторов
*/

public class Solution {
    String s;
    int i;
    Object o;

    public Solution() {
    }

    protected Solution(String s) {
        this.s = s;
    }

    Solution(String s, int i) {
        this.s = s;
        this.i = i;

    }
    private Solution(String s, int i, Object o) {
        this.s = s;
        this.i = i;
        this.o = o;

    }

    public static void main(String[] args) {

    }
}

