package com.javarush.task.task37.task3707;

import java.io.*;

public class Test {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        AmigoSet<String> set1 = new AmigoSet<>();
        for (int i = 0; i < 5; i++) {
            set1.add("entry" + i);
        }
        set1.forEach(e -> System.out.print(e + " "));
        System.out.println("\n\n");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(set1);
        oos.close();
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        AmigoSet<String> set2 = (AmigoSet) ois.readObject();
        ois.close();
        System.out.println(set2.toString());
    }
}
