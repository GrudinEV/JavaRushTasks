package com.javarush.task.task35.task3507;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

/* 
ClassLoader - что это такое?
*/
public class Solution {
    public static void main(String[] args) {
        Set<? extends Animal> allAnimals = getAllAnimals(/*Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath()*/"/E:/Программирование/JavaRushTasks/out/production/4.JavaCollections/" + Solution.class.getPackage().getName().replaceAll("[.]", "/") + "/data");
        System.out.println(allAnimals);
    }

    public static Set<? extends Animal> getAllAnimals(String pathToAnimals) {
        Path path = Path.of(pathToAnimals.substring(1));
        File[] arrayFiles = path.toFile().listFiles();
        Set<Animal> set = new HashSet<>();
        for (File file : arrayFiles) {
            MyClassloader mcl = new MyClassloader(file.toString());
            Class<? extends Animal> clazz = null;
            try {
                clazz = (Class<? extends Animal>) Class.forName("com.javarush.task.task35.task3507.data.Cat", true, mcl);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            try {
                set.add(clazz.getDeclaredConstructor().newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
//            try {
//                Class<? extends Animal> clazz = (Class<? extends Animal>) Class.forName(file.getName().replaceFirst(".class", ""));
//                Animal animal = clazz.getDeclaredConstructor().newInstance();
//                System.out.println(animal);
//                set.add(clazz.getDeclaredConstructor().newInstance());
//            } catch (ClassNotFoundException e) {
//                System.out.println(e);
//            } catch (InstantiationException e) {
//                e.printStackTrace();
//            } catch (InvocationTargetException e) {
//                e.printStackTrace();
//            } catch (NoSuchMethodException e) {
//                e.printStackTrace();
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            }

//            System.out.println(file.getName().replaceFirst(".class", ""));
//            try (FileInputStream fis = new FileInputStream(file);
//                    DataInputStream ois = new DataInputStream(fis)) {
//                Object o = ois.
//                Class<? extends Animal> clazz = (Class<? extends Animal>) o.getClass();
//                Animal animal = clazz.getDeclaredConstructor().newInstance();
//                set.add(animal);
//            } catch (IOException /*| ClassNotFoundException*/ e) {
//                e.printStackTrace();
//            } /*catch (InstantiationException e) {
//                e.printStackTrace();
//            } catch (InvocationTargetException e) {
//                e.printStackTrace();
//            } catch (NoSuchMethodException e) {
//                e.printStackTrace();
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            }*/
        }
        return set;
    }

    static class MyClassloader extends ClassLoader
    {
        public final String classPath;

        public MyClassloader(String classPath)
        {
            this.classPath= classPath;
        }

        @Override
        protected synchronized Class loadClass(String name, boolean resolve) throws ClassNotFoundException
        {
            File f = new File(classPath);
            System.out.println(name);
            byte[] classBytes= new byte[0];
            try {
                classBytes = loadFileAsBytes(f);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Class result = defineClass(name, classBytes, 0, classBytes.length);
            return result;
        }

//        protected Class findClass(String name) throws ClassNotFoundException
//        {
//            Class result= (Class)classesHash.get(name);
//            if (result!=null) {
//                /*
//                 * System.out.println("% Class " + name +
//                 *                       " found in cache");
//                 */
//                return result;
//            }
//
//            File f= findFile(name.replace('.','/'),".class");
//            // Класс mypackage.MyClass следует искать файле
//            // mypackage/MyClass.class
//            /*
//             * System.out.println("% Class " + name +
//             *                    (f==null?"":" found in "+f));
//             */
//            if (f==null) {
//                // Обращаемся к системному загрузчику в случае
//                // неудачи. findSystemClass – это метод
//                // абстрактного класса ClassLoader с объявлением
//                // protected Class findSystemClass(String name)
//                // (т.е. предназначенный для использования в
//                // наследниках и не подлежащий переопределению).
//                // Он выполняет поиск и загрузку класса по
//                // алгоритму системного загрузчика. Без вызова
//                // findSystemClass(name) нам пришлось бы
//                // самостоятельно позаботиться о загрузке всех
//                // стандартных библиотечных классов типа
//                // java.lang.String, что потребовало бы
//                // реализовать работу с JAR-архивами
//                // (стандартные библиотеки почти всегда
//                // упакованы в JAR)
//                return findSystemClass(name);
//            }
//
//            try {
//                byte[] classBytes= loadFileAsBytes(f);
//                result= defineClass(name, classBytes, 0,
//                        classBytes.length);
//            } catch (IOException e) {
//                throw new ClassNotFoundException(
//                        "Cannot load class " + name + ": " + e);
//            } catch (ClassFormatError e) {
//                throw new ClassNotFoundException(
//                        "Format of class file incorrect for class "
//                                + name + " : " + e);
//            }
//            classesHash.put(name,result);
//            return result;
//        }
//        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//        protected java.net.URL findResource(String name)
//        {
//            File f= findFile(name, "");
//            if (f==null)
//                return null;
//            try {
//                return f.toURL();
//            } catch(java.net.MalformedURLException e) {
//                return null;
//            }
//        }
//        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//        /**
//         * Поиск файла с именем name и, возможно, расширением
//         * extension в каталогах поиска, заданных параметром
//         * конструктора classPath. Имена подкаталогов в name
//         * разделяются символом '/' – даже если в операционной
//         * системе принят другой разделитель для подкаталогов.
//         * (Именно в таком виде получает свой параметр метод
//         * findResource.)
//         */
//        private File findFile(String name, String extension)
//        {
//            File fl
//            for (int k=0; k <classPath.length; k++) {
//                f = new File((new File(classPath[k])).getPath()
//                        + File.separatorChar
//                        + name.replace('/',
//                        File.separatorChar)
//                        + extension);
//                if (f.exists())
//                    return f;
//            }
//            return null;
//        }
//        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        public static byte[] loadFileAsBytes(File file)
                throws IOException
        {
            byte[] result = new byte[(int)file.length()];
            FileInputStream f = new FileInputStream(file);
            try {
                f.read(result,0,result.length);
            } finally {
                try {
                    f.close();
                } catch (Exception e) {
                    // Игнорируем исключения, возникшие при
                    // вызове close. Они крайне маловероятны и не
                    // очень важны - файл уже прочитан. Но если
                    // они все же возникнут, то они не должны
                    // замаскировать действительно важные ошибки,
                    // возникшие при вызове read.
                };
            }
            return result;
        }
//        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    }
}
