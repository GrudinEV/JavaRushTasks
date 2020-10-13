package com.javarush.task.task12.task1217;

/* 
Лететь, бежать и плыть
*/

public class Solution {
    public static void main(String[] args) {

    }

//add interfaces here - добавь интерфейсы тут
    public interface CanFly {
        void takeoff();
    }
    public interface CanRun {
        int setSpeed(int a);
    }
    public interface CanSwim {
        void setImmersionDepht(int a);
    }
}
