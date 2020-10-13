package com.javarush.task.task21.task2104;

import java.util.HashSet;
import java.util.Set;

/* 
Equals and HashCode
*/
public class Solution {
    private final String first, last;

    public Solution(String first, String last) {
        this.first = first;
        this.last = last;
    }

    public boolean equals(Object n) {
        if (this == n) return true;
        if (n.getClass() == this.getClass()) {
            Solution n1 = (Solution) n;
            if (first == n1.first && last == n1.last) return true;
        }
        System.out.println(getClass().getName());
        return false;
//        return n.first.equals(first) && n.last.equals(last);
    }

    public int hashCode() {
        return 31 * (first != null ? first.hashCode() : 0) + (last != null ? last.hashCode() : 0);
    }

    public static void main(String[] args) {
        Set<Solution> s = new HashSet<>();
        s.add(new Solution("Donald", null));
        System.out.println(s.contains(new Solution("Donald", null)));
    }
}
