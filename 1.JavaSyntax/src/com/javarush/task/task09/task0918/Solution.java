package com.javarush.task.task09.task0918;

/* 
Все свои, даже исключения
*/

import java.io.FileNotFoundException;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) {
    }
    
    static class MyException extends FileNotFoundException {
    }

    static class MyException2 extends IOException {
    }

    static class MyException3 extends IndexOutOfBoundsException{
    }

    static class MyException4 extends RuntimeException {
    }
}

