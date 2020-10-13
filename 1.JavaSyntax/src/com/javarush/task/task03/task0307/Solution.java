package com.javarush.task.task03.task0307;

/* 
Привет StarCraft!
*/

public class Solution {
    public static void main(String[] args) {
        Zerg zerg1 = new Zerg();
        zerg1.name = "Zerg Working";
        Zerg zerg2 = new Zerg();
        zerg2.name = "Zerg Warior";
        Zerg zerg3 = new Zerg();
        zerg3.name = "Zerg Archer";
        Zerg zerg4 = new Zerg();
        zerg4.name = "Zerg Wizard";
        Zerg zerg5 = new Zerg();
        zerg5.name = "Zerg Bard";

        Protoss protoss1 = new Protoss();
        protoss1.name = "Protoss Warior";
        Protoss protoss2 = new Protoss();
        protoss2.name = "Protoss Archer";
        Protoss protoss3 = new Protoss();
        protoss3.name = "Protoss Bard";

        Terran terran1 = new Terran();
        terran1.name = "Terran Working";
        Terran terran2 = new Terran();
        terran2.name = "Terran Warior";
        Terran terran3 = new Terran();
        terran3.name = "Terran Archer";
        Terran terran4 = new Terran();
        terran4.name = "Terran Wizard";
        //напишите тут ваш код

    }

    public static class Zerg {
        public String name;

        // public Zerg(String name) {
        //     this.name = name;
        // }
    }

    public static class Protoss {
        public String name;

        // public Protoss(String name) {
        //     this.name = name;
        // }
    }

    public static class Terran {
        public String name;

        // public Terran(String name) {
        //     this.name = name;
        // }
    }


}
