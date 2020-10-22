package com.javarush.task.task35.task3507;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;

/* 
ClassLoader - что это такое?
*/
public class Solution {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Set<? extends Animal> allAnimals = getAllAnimals(/*"E:\\Программирование\\JavaRushTasks\\out\\production\\4.JavaCollections\\"*/Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() + Solution.class.getPackage().getName().replaceAll("[.]", "/") + "/data");
        System.out.println(allAnimals);
    }

    public static Set<? extends Animal> getAllAnimals(String pathToAnimals) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        File dir = new File(pathToAnimals);
        File[] files = dir.listFiles();
        Set<Animal> set = new HashSet<>();
        for (File file : files) {
            MyClassLoader mcl = new MyClassLoader(file.getAbsolutePath());
            Class<?> clazz = Class.forName(Solution.class.getPackage().getName() + ".data." + file.getName().replaceAll("\\.class", ""), true, mcl);
            Constructor<?>[] constructors = clazz.getConstructors();
            if (constructors.length > 0) {
                for (Constructor<?> constructor : constructors) {
                    if (constructor.getParameterCount() == 0) {
                        Animal animal = (Animal) clazz.getDeclaredConstructor().newInstance();
                        if (animal instanceof Animal) {
                            set.add((Animal) clazz.getDeclaredConstructor().newInstance());
                        }
                    }
                }
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
        protected Class<?> findClass(String name){
            long length = new File(classPath).length();
            byte[] bytes = new byte[(int)length];
            int offset = 0;
            int numRead = 0;
            try (InputStream is = new FileInputStream(new File(classPath))) {
                while (true) {
                        if (!(offset < bytes.length && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0)) {
                            break;
                        }
                    offset += numRead;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return defineClass(name, bytes, 0, bytes.length);
        }
    }
}
