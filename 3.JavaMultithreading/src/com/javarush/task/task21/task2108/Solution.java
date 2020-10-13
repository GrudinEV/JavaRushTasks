package com.javarush.task.task21.task2108;

import java.util.Arrays;

/*
Клонирование растений
*/
public class Solution {
    public static void main(String[] args) {
        Tree tree = new Tree("willow", new String[]{"s1", "s2", "s3", "s4"});
        Tree clone = null;
        clone = (Tree) tree.clone();

        System.out.println(tree);
        System.out.println(clone);

        System.out.println(tree.branches);
        System.out.println(clone.branches);
        System.out.println(tree.equals(clone));
        System.out.println(tree.branches.equals(clone.branches));
        System.out.println(tree.branches == clone.branches);
        for (String s : tree.branches) {
            System.out.print(s + " ");
        }
        System.out.println("");
        for (String s : clone.branches) {
            System.out.print(s + " ");
        }
        System.out.println("");
        System.out.println(tree.hashCode() + " " + clone.hashCode());
        System.out.println(tree.getBranches().hashCode() + " " + clone.getBranches().hashCode());
    }

    public static class Plant {
        private String name;

        public Plant(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public static class Tree extends Plant implements Cloneable {
        private String[] branches;

        public Tree(String name, String[] branches) {
            super(name);
            this.branches = branches;
        }
        public Object clone() {
            String[] array = this.getBranches().clone();
            Object o = new Tree(this.getName(), array);
            return o;
        }

        public boolean equals(Object o) {
            if (o == null) return false;
            if (!(o instanceof Tree)) return false;
            Tree t = (Tree) o;
            if (this.getName() != t.getName()) return false;
            if (this.getBranches().length != t.getBranches().length) return false;
            for (int i = 0; i < this.getBranches().length; i++) {
                if (this.getBranches()[i] != t.getBranches()[i]) return false;
            }
            return true;
        }
        public int hashCode() {
            int result = (this.getName() != null ? this.getName().hashCode() : 0);
            for (int i = 0; i < this.getBranches().length; i++) {
                result = 31 * result + (this.getBranches()[i] != null ? this.getBranches()[i].hashCode() : 0);
            }
            return result;
        }

        public String[] getBranches() {
            return branches;
        }
    }
}
