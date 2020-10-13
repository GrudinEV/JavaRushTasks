package com.javarush.task.task07.task0724;

/* 
Семейная перепись
*/

public class Solution {
    public static void main(String[] args) {
        // напишите тут ваш код
        Human fatherOfFather = new Human("дедушка Вася", true, 65);
        Human motherOfFather = new Human("бабушка Нина", false, 61);
        Human fatherOfMother = new Human("дедушка Игорь", true, 71);
        Human motherOfMother = new Human("бабушка Екатерина", false, 67);
        Human father = new Human("папа Владимир", true, 43, fatherOfFather, motherOfFather);
        Human mother = new Human("мама Ульяна", false, 41, fatherOfMother, motherOfMother);
        Human sun = new Human("сын Илья", true, 21, father, mother);
        Human daughter1 = new Human("дочь Клава", false, 18, father, mother);
        Human daughter2 = new Human("дочь Изольда", false, 16, father, mother);
        System.out.println(fatherOfFather.toString());
        System.out.println(motherOfFather.toString());
        System.out.println(fatherOfMother.toString());
        System.out.println(motherOfMother.toString());
        System.out.println(father.toString());
        System.out.println(mother.toString());
        System.out.println(sun.toString());
        System.out.println(daughter1.toString());
        System.out.println(daughter2.toString());
    }

    public static class Human {
        // напишите тут ваш код
        String name;
        boolean sex;
        int age;
        Human father, mother;
        public Human(String name, boolean sex, int age) {
            this.name = name;
            this.age = age;
            this.sex = sex;
        }
        public Human(String name, boolean sex, int age,Human father, Human mother) {
            this.name = name;
            this.sex = sex;
            this.age = age;
            this.father = father;
            this.mother = mother;
        }


        public String toString() {
            String text = "";
            text += "Имя: " + this.name;
            text += ", пол: " + (this.sex ? "мужской" : "женский");
            text += ", возраст: " + this.age;

            if (this.father != null) {
                text += ", отец: " + this.father.name;
            }

            if (this.mother != null) {
                text += ", мать: " + this.mother.name;
            }

            return text;
        }
    }
}