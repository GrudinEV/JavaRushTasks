package com.javarush.task.task26.task2602;

import java.util.Set;
import java.util.TreeSet;

/* 
Был бы ум - будет и успех
*/
public class Solution {
    public static void main(String[] args) {
        Set<Soldier> soldiers = new TreeSet<>();
        soldiers.add(new Soldier("Ivanov", 170));
        soldiers.add(new Soldier("Petrov", 185));
        soldiers.add(new Soldier("Sidorov", 175));

        for (Soldier soldier : soldiers) {
            System.out.println(soldier.name);
        }
    }

    public static class Soldier implements Comparable {
        private String name;
        private int height;

        public Soldier(String name, int height) {
            this.name = name;
            this.height = height;
        }

        public int compareTo(Soldier solder) {
            return solder.height - this.height;
        }

        @Override
        public int compareTo(Object o) {
            if (o == null) {
                return -1;
            }
            if (!(o instanceof Soldier)) {
                return -1;
            }
            Soldier soldier = (Soldier) o;

            return compareTo(soldier);
        }
    }
}
