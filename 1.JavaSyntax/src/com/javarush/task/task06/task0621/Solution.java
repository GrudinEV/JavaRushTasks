package com.javarush.task.task06.task0621;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Родственные связи кошек
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String grandFatherName = reader.readLine();
        Cat catGrandFather = new Cat(grandFatherName);

        String grandMotherName = reader.readLine();
        Cat catGrandMother = new Cat(grandMotherName);

        String fatherName = reader.readLine();
        Cat catFather = new Cat(catGrandFather, fatherName);

        String motherName = reader.readLine();
        Cat catMother = new Cat(motherName, catGrandMother);

        String sunName = reader.readLine();
        Cat catSun = new Cat(catFather, sunName,catMother);

        String daughterName = reader.readLine();
        Cat catDaughter = new Cat(catFather, daughterName, catMother);

        System.out.println(catGrandFather);
        System.out.println(catGrandMother);
        System.out.println(catFather);
        System.out.println(catMother);
        System.out.println(catSun);
        System.out.println(catDaughter);
    }

    public static class Cat {
        private String name;
        private Cat father;
        private Cat mother;

        Cat(String name) {
            this.name = name;
        }

        Cat(Cat parent, String name) {
            this.name = name;
            this.father = parent;
        }

        Cat(String name, Cat parent) {
            this.name = name;
            this.mother = parent;
        }

        Cat(Cat parent1, String name, Cat parent2) {
            this.name = name;
            this.father = parent1;
            this.mother = parent2;
        }

        @Override
        public String toString() {
            if (father == null && mother == null)
                return "The cat's name is " + name + ", no mother, no father";
            else
                if (father == null) return "The cat's name is " + name + ", mother is " + mother.name + ", no father";
                else
                    if (mother == null) return "The cat's name is " + name + ", no mother, father is " + father.name;
                    else return "The cat's name is " + name + ", mother is " + mother.name +", father is " + father.name;
        }
    }

}
