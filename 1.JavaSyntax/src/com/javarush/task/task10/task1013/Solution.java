package com.javarush.task.task10.task1013;

/* 
Конструкторы класса Human
*/

public class Solution {
    public static void main(String[] args) {
    }

    public static class Human {
        // Напишите тут ваши переменные и конструкторы
        private String name;
        private byte age;
        private int height;
        private boolean sex;
        private Human father;
        private Human mother;

        public Human() {

        }

        public Human(String name) {
            this.name = name;
        }

        public Human(byte age) {
            this.age = age;
        }

        public Human(int height) {
            this.height = height;
        }

        public Human(boolean sex) {
            this.sex = sex;
        }

        public Human(Human father, Human mother) {
            this.father = father;
            this.mother = mother;
        }

        public Human(String name, byte age) {
            this.name = name;
            this.age = age;
        }

        public Human(String name, byte age, int height) {
            this.name = name;
            this.age = age;
            this.height = height;
        }

        public Human(String name, byte age, int height, boolean sex) {
            this.name = name;
            this.age = age;
            this.height = height;
            this.sex = sex;
        }

        public Human(String name, byte age, int height, boolean sex, Human father, Human mother) {
            this.name = name;
            this.age = age;
            this.height = height;
            this.sex = sex;
            if (!father.equals(null)) this.father = father;
            if (!mother.equals(null)) this.mother = mother;
        }
    }
}
