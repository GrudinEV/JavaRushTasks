package com.javarush.task.task07.task0707;

import java.util.ArrayList;

/* 
Что за список такой?
*/

public class Solution {
    public static void main(String[] args) {
        //напишите тут ваш код
        ArrayList<String> list = new ArrayList<>();
        list.add("afkjgk"); list.add("sdgvomikmlgdsa"); list.add("dskjvnrib"); list.add("dsgouqngdcn"); list.add("sadgvkinviqu");
        System.out.println(list.size());
        for (int i = 0; i < list.size(); i++)
            System.out.println(list.get(i));
    }
}
