package com.javarush.task.task20.task2003;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/*
Знакомство с properties
*/
public class Solution {
    public static Map<String, String> properties = new HashMap<>();

    public void fillInPropertiesMap() throws Exception {
        //implement this method - реализуйте этот метод
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream inputStream = new FileInputStream(reader.readLine());
        reader.close();
        load(inputStream);
        inputStream.close();
    }

    public void save(OutputStream outputStream) throws Exception {
        //implement this method - реализуйте этот метод
        Properties properties = new Properties();
//        for (Map.Entry<String, String> pair : Solution.properties.entrySet()) {
//            properties.setProperty(pair.getKey(), pair.getValue());
//        }
        properties.putAll(Solution.properties);
        properties.store(outputStream, "");
    }

    public void load(InputStream inputStream) throws Exception {
        //implement this method - реализуйте этот метод
        Properties properties = new Properties();
        properties.load(inputStream);
//        for (Map.Entry<Object, Object> pair : properties.entrySet()) {
//            Solution.properties.put((String) pair.getKey(), (String) pair.getValue());
//        }
        Solution.properties.putAll(new HashMap(properties));
    }

    public static void main(String[] args) throws Exception {
//        properties.put("website", "https://ru.wikipedia.org/");
//        properties.put("language", "Russian");
//        properties.put("message", "This task is krazy");
//        FileOutputStream out = new FileOutputStream("f:\\1.properties");
//        new Solution().save(out);
//        out.close();

    }
}
