package com.javarush.task.task05.task0526;

/* 
Мужчина и женщина
*/

public class Solution {
    public static void main(String[] args) {
        //напишите тут ваш код
        Man manVasa = new Man("Vasa", 30, "Perm");
        Man manPasha = new Man("Pasha", 25, "Moscow");
        Woman womanJenya = new Woman("Jenya", 34,"Perm");
        Woman womanTanya = new Woman("Tanya", 36, "Perm");
        System.out.println(manVasa.name + " " + manVasa.age + " " + manVasa.address);
        System.out.println(manPasha.name + " " + manPasha.age + " " + manPasha.address);
        System.out.println(womanJenya.name + " " + womanJenya.age + " " + womanJenya.address);
        System.out.println(womanTanya.name + " " + womanTanya.age + " " + womanTanya.address);
    }

    //напишите тут ваш код
    public static class Man {
        private String name;
        private int age;
        private String address;
        public Man(String name, int age, String address) {
            this.name = name;
            this.age = age;
            this.address = address;
        }
    }

    public  static  class Woman {
        private String name;
        private int age;
        private String address;
        public Woman(String name, int age, String address) {
            this.name = name;
            this.age = age;
            this.address = address;
        }
    }
}
