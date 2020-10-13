package com.javarush.task.task08.task0824;

import java.util.ArrayList;
import java.util.List;

/* 
Собираем семейство
*/

public class Solution {
    public static void main(String[] args) {
        //напишите тут ваш код
        Human child1 = new Human("сын Вася", true, 11);
        Human child2 = new Human("сын Петя", true,13);
        Human child3 = new Human("дочь Ирина", false,10);
        Human father = new Human("папа Владимир", true,39, child1, child2, child3);
        Human mother = new Human("мама Нина", false,34, child1, child2, child3);
        Human fatherOfFather = new Human("дедушка Боря", true,69, father);
        Human motherOfFather = new Human("бабушка Екатерина", false,65, father);
        Human fatherOfMother = new Human("дедушка Игорь", true,79, mother);
        Human MotherOfMother = new Human("бабушка Ирина", false,71, mother);
        System.out.println(child1);
        System.out.println(child2);
        System.out.println(child3);
        System.out.println(father);
        System.out.println(mother);
        System.out.println(fatherOfFather);
        System.out.println(motherOfFather);
        System.out.println(fatherOfMother);
        System.out.println(fatherOfMother);

    }

    public static class Human {
        //напишите тут ваш код
        String name;
        boolean sex;
        int age;
        ArrayList<Human> children = new ArrayList<>();

        private Human(String name, boolean sex, int age) {
            this.name = name;
            this.sex = sex;
            this.age = age;
        }

        private Human(String name, boolean sex, int age, Human child1) {
            this.name = name;
            this.sex = sex;
            this.age = age;
            this.children.add(child1);
        }

        private Human(String name, boolean sex, int age, Human child1, Human child2, Human child3) {
            this.name = name;
            this.sex = sex;
            this.age = age;
            this.children.add(child1);
            this.children.add(child2);
            this.children.add(child3);
        }

        public String toString() {
            String text = "";
            text += "Имя: " + this.name;
            text += ", пол: " + (this.sex ? "мужской" : "женский");
            text += ", возраст: " + this.age;

            int childCount = this.children.size();
            if (childCount > 0) {
                text += ", дети: " + this.children.get(0).name;

                for (int i = 1; i < childCount; i++) {
                    Human child = this.children.get(i);
                    text += ", " + child.name;
                }
            }
            return text;
        }
    }
}
