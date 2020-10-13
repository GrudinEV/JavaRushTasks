package com.javarush.task.task20.task2024;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

/* 
Знакомство с графами
*/
public class Solution implements Serializable {
    int node;
    List<Solution> edges = new LinkedList<>();

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Solution solution1 = new Solution();
        solution1.node = 1;
        Solution solution2 = new Solution();
        solution2.node = 2;
        Solution solution3 = new Solution();
        solution3.node = 3;
        Solution solution4 = new Solution();
        solution4.node = 4;
        Solution solution5 = new Solution();
        solution5.node = 5;
        Solution solution6 = new Solution();
        solution6.node = 6;
        solution1.edges.add(solution2);
        solution2.edges.add(solution2);
        solution2.edges.add(solution4);
        solution2.edges.add(solution5);
        solution4.edges.add(solution1);
        solution4.edges.add(solution5);
        solution5.edges.add(solution4);
        solution6.edges.add(solution3);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(solution1);
        oos.writeObject(solution2);
        oos.writeObject(solution3);
        oos.writeObject(solution4);
        oos.writeObject(solution5);
        oos.writeObject(solution6);

        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        Solution newSolution1 = (Solution) ois.readObject();
        Solution newSolution2 = (Solution) ois.readObject();
        Solution newSolution3 = (Solution) ois.readObject();
        Solution newSolution4 = (Solution) ois.readObject();
        Solution newSolution5 = (Solution) ois.readObject();
        Solution newSolution6 = (Solution) ois.readObject();


        System.out.println(solution2);
        System.out.println(solution2.node);
        for (Solution s : solution2.edges)
            System.out.print(s + " ");
        System.out.println("");
        System.out.println("");
        System.out.println(newSolution2);
        System.out.println(newSolution2.node);
        for (Solution s : newSolution2.edges)
            System.out.print(s + " ");



    }
}
