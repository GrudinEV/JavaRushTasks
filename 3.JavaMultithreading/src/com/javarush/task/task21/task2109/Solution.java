package com.javarush.task.task21.task2109;

/*
Запретить клонирование
*/
public class Solution {
    public static class A implements Cloneable {
        private int i;
        private int j;

        public A(int i, int j) {
            this.i = i;
            this.j = j;
        }

        public int getI() {
            return i;
        }

        public int getJ() {
            return j;
        }
    }

    public static class B extends A {
        private String name;

        public B(int i, int j, String name) {
            super(i, j);
            this.name = name;
        }
        public Object clone() throws CloneNotSupportedException {
            throw new CloneNotSupportedException();
        }

        public String getName() {
            return name;
        }
    }

    public static class C extends B {
        public C(int i, int j, String name) {
            super(i, j, name);
        }

        public Object clone() {
            return new C(this.getI(), this.getJ(), this.getName());
        }

        public boolean equals(Object o) {
            if (o == null) return false;
            if (!(o instanceof C)) return false;
            C c = (C) o;
            if (c.getI() != this.getI()) return false;
            if (c.getJ() != this.getJ()) return false;
            if (c.getName() != null ? c.getName() != this.getName() : this.getName() != null) return false;
            return true;
        }
        public int hashCode() {
            int result = this.getI();
            result = 31 * result + this.getJ();
            result = 31 * result + (this.getName() != null ? this.getName().hashCode() : 0);
            return result;
        }
    }

    public static void main(String[] args) throws CloneNotSupportedException {
//        C c = new C(1, 2, "Kek");
//        C clone = (C) c.clone();
//        System.out.println(c);
//        System.out.println(clone);
//        System.out.println(clone.equals(c));

    }
}
