package com.javarush.task.task36.task3606;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
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
        Solution solution = new Solution(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "com/javarush/task/task36/task3606/data/second");
        solution.scanFileSystem();
        System.out.println(solution.getHiddenClassObjectByKey("secondhiddenclassimpl"));
        System.out.println(solution.getHiddenClassObjectByKey("firsthiddenclassimpl"));
        System.out.println(solution.getHiddenClassObjectByKey("packa"));
    }

    public void scanFileSystem() throws ClassNotFoundException {
        try {
            packageName = java.net.URLDecoder.decode(packageName, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
        }
        String sep = System.getProperty("file.separator");
        if(!(packageName.endsWith(sep))){
            packageName = packageName.concat(sep);
        }
        File[] files = new File(packageName).listFiles();
        for (File file : files) {
            if (file.toString().endsWith(".class")) {
                MyClassLoader mcl = new MyClassLoader(file.toString());
                String name = "com.javarush.task.task36.task3606.data.second." + file.getName().replace(".class", "");
                Class clazz = Class.forName(name, true, mcl);
                hiddenClasses.add(clazz);
            }
        }
    }

    public HiddenClass getHiddenClassObjectByKey(String key) {
        Constructor<HiddenClass> constructor = null;
        Class<HiddenClass> clazz = hiddenClasses.stream().filter(x -> x.getSimpleName().toLowerCase().startsWith(key.toLowerCase())).collect(Collectors.toList()).get(0);
        Class[] interfaces = clazz.getInterfaces();
        for (Class interfase : interfaces) {
            if (interfase.getSimpleName().equals("HiddenClass")) {
                Constructor[] constructors = clazz.getDeclaredConstructors();
                for (Constructor constr : constructors) {
                    if (constr.getParameterTypes().length == 0) {
                        constructor = (Constructor<HiddenClass>) constr;
                    }
                }
                constructor.setAccessible(true);
                try {
                    return constructor.newInstance();
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public class MyClassLoader extends ClassLoader {
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
            return defineClass(null, bytes, 0, bytes.length);
        }
    }
}

