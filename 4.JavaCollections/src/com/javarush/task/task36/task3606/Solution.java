package com.javarush.task.task36.task3606;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/* 
Осваиваем ClassLoader и Reflection
*/

public class Solution {
    private List<Class> hiddenClasses = new ArrayList<>();
    private String packageName;

    public Solution(String packageName) {
        this.packageName = packageName;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Solution solution = new Solution(/*Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath()*/"/E:/Программирование/JavaRushTasks/out/production/4.JavaCollections/" + "com/javarush/task/task36/task3606/data/second");
        solution.scanFileSystem();
        System.out.println(solution.getHiddenClassObjectByKey("secondhiddenclassimpl"));
        System.out.println(solution.getHiddenClassObjectByKey("firsthiddenclassimpl"));
        System.out.println(solution.getHiddenClassObjectByKey("packa"));
    }

    public void scanFileSystem() throws ClassNotFoundException {
        List<File> files = Arrays.stream(new File(packageName).listFiles()).filter(x -> x.toString().endsWith(".class")).collect(Collectors.toList());
        for (File file : files) {
            MyClassLoader mcl = new MyClassLoader(file.toString());
            String name = "com/javarush/task/task36/task3606/data/second/".replaceAll("/", ".") + file.getName().replace(".class", "");
            Class clazz = Class.forName(name, true, mcl);
            hiddenClasses.add(clazz);
        }
    }

    public HiddenClass getHiddenClassObjectByKey(String key) {
        Constructor<HiddenClass> constructor = null;
        Class<HiddenClass> clazz = hiddenClasses.stream().filter(x -> x.getSimpleName().toLowerCase().startsWith(key)).collect(Collectors.toList()).get(0);
        try {
            constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(true);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        try {
            return constructor.newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static class MyClassLoader extends ClassLoader {
        private final String path;

        public MyClassLoader(String path) {
            this.path = path;
        }

        @Override
        protected Class<?> findClass(String name) {
            byte[] bytes = null;
            try (FileInputStream isr = new FileInputStream(path)) {
                bytes = new byte[isr.available()];
                while (isr.available() > 0) {
                    isr.read(bytes);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return defineClass(name, bytes, 0, bytes.length);
        }
    }
}

