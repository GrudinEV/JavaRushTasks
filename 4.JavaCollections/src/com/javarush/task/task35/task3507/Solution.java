package com.javarush.task.task35.task3507;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;

/* 
ClassLoader - что это такое?
*/
public class Solution {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Set<? extends Animal> allAnimals = getAllAnimals(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() + Solution.class.getPackage().getName().replaceAll("[.]", "/") + "/data");
        System.out.println(allAnimals);
    }

    public static Set<? extends Animal> getAllAnimals(String pathToAnimals) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        File dir = new File(pathToAnimals);
        File[] files = dir.listFiles();
        Set<Animal> set = new HashSet<>();
        for (File file : files) {
            MyClassLoader mcl = new MyClassLoader(file.getAbsolutePath());
            Class<?> clazz = Class.forName(Solution.class.getPackage().getName() + ".data." + file.getName().replaceAll("\\.class", ""), true, mcl);
            if (clazz instanceof Class) {
                set.add((Animal) clazz.getDeclaredConstructor().newInstance());
            }
        }
        return set;
    }

    static class MyClassLoader extends ClassLoader {
        final String classPath;

        MyClassLoader(String classPath) {
            this.classPath = classPath;
        }

        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            InputStream is = null;
            try {
                is = new FileInputStream(new File(classPath));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            long length = new File(classPath).length();
            byte[] bytes = new byte[(int)length];

            int offset = 0;
            int numRead = 0;
            while (true) {
                try {
                    if (!(offset < bytes.length
                                        && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0)) break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                offset += numRead;
            }

            if (offset < bytes.length) {
                try {
                    throw new IOException("Could not completely read file " + classPath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return defineClass(name, bytes, 0, bytes.length);
        }
    }
}
