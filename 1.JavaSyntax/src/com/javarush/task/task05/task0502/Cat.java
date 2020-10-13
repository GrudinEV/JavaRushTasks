package com.javarush.task.task05.task0502;

/* 
Реализовать метод fight
*/

public class Cat {
    public int age;
    public int weight;
    public int strength;

    public Cat() {
    }

    public boolean fight(Cat anotherCat) {
        //напишите тут ваш код
        int i = 0, j = 0;
        if (this.age > anotherCat.age) i++; else if (this.age < anotherCat.age) j++;
        if (this.weight > anotherCat.weight) i++; else if (this.weight < anotherCat.weight) j++;
        if (this.strength > anotherCat.strength) i++; else if (this.strength < anotherCat.strength) j++;
        return (i > j);
    }

    public static void main(String[] args) {

    }
}
