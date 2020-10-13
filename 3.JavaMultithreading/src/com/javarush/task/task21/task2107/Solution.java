package com.javarush.task.task21.task2107;

import java.util.LinkedHashMap;
import java.util.Map;

/* 
Глубокое клонирование карты
*/
public class Solution implements Cloneable {

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.users.put("Hubert", new User(172, "Hubert"));
        solution.users.put("Zapp", new User(41, "Zapp"));
        Solution clone = null;
//        try {
            clone = solution.clone();
            System.out.println(solution);
            System.out.println(clone);

            System.out.println(solution.users);
            System.out.println(clone.users);

        System.out.println(solution.users.equals(clone.users));

//        } catch (CloneNotSupportedException e) {
//            e.printStackTrace(System.err);
//        }
    }
    protected Solution clone() {
        Solution sol = new Solution();
        this.users.forEach((key, value) ->
                sol.users.put(key, value.clone()));
        return sol;
    }
    public boolean equals(Object o) {
        if (o == null) return false;
        if (!(o instanceof Solution)) return false;
        Solution s = (Solution) o;
        if (this.users.size() != s.users.size()) return false;
        for (Map.Entry<String, User> pair : this.users.entrySet()) {
            if (!(s.users.containsKey(pair.getKey()))) {
                return false;
            } else {
              if (!(s.users.get(pair.getKey())).equals(pair.getValue())) {
                  return false;
              }
            }
        }
        return true;
    }
    public int hashCode() {
        int result = 0;
        for (Map.Entry<String, User> pair : this.users.entrySet()) {
            result = 31 * result + pair.getKey().hashCode();
            result = result * 31 + (pair.getValue() != null ? pair.getValue().hashCode() : 0);
        }
        return result;
    }

    protected Map<String, User> users = new LinkedHashMap();

    public static class User implements Cloneable {
        int age;
        String name;

        public User(int age, String name) {
            this.age = age;
            this.name = name;
        }
        protected User clone() {
            User user = new User(this.age, this.name);
            return user;
        }
        public boolean equals(Object o) {
            if (o == null) return false;
            if (!(o instanceof User)) return false;
            User u = (User) o;
            if (this.age != u.age) return false;
            if (this.name != u.name) return false;
            return true;
        }

        public int hashCode() {
            return 31 * age + (name == null ? 0 : name.hashCode());
        }
    }
}
