package com.javarush.task.task08.task0820;

import java.util.HashSet;
import java.util.Set;

/* 
Множество всех животных
*/

public class Solution {
    public static void main(String[] args) {
        Set<Cat> cats = createCats();
        Set<Dog> dogs = createDogs();

        Set<Object> pets = join(cats, dogs);
        printPets(pets);

        removeCats(pets, cats);
        printPets(pets);
    }

    public static Set<Cat> createCats() {
        Set<Cat> result = new HashSet<Cat>();

        //напишите тут ваш код
        for (int i = 0; i < 4; i++)
            result.add(new Cat());
        return result;
    }

    public static Set<Dog> createDogs() {
        //напишите тут ваш код
        Set<Dog> dogs = new HashSet<>();
        for (int i = 0; i < 3; i++)
            dogs.add(new Dog());
        return dogs;
    }

    public static Set<Object> join(Set<Cat> cats, Set<Dog> dogs) {
        //напишите тут ваш код
        Set<Object> manyAnimals = new HashSet<>();
        manyAnimals.addAll(cats);
        manyAnimals.addAll(dogs);
        return manyAnimals;
    }

    public static void removeCats(Set<Object> pets, Set<Cat> cats) {
        //напишите тут ваш код
        Set<Object> copyPets = new HashSet<>(pets);
        if (copyPets != null && cats != null)
        for (Object pet : copyPets)
            for (Cat cat : cats)
                if (cat.equals(pet)) pets.remove(pet);
        copyPets = null;
    }

    public static void printPets(Set<Object> pets) {
        //напишите тут ваш код
        if (pets != null)
            for (Object pet : pets)
                System.out.println(pet);

    }

    //напишите тут ваш код
    public static class Cat {

        public Cat() {

        }
    }
    public static class Dog {
        public Dog() {

        }
    }
}
