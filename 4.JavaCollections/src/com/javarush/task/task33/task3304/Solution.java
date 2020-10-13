package com.javarush.task.task33.task3304;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

/* 
Конвертация из одного класса в другой используя JSON Ӏ 3304
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        Second s = (Second) convertOneToAnother(new First(), Second.class);
        First f = (First) convertOneToAnother(new Second(), First.class);
//        StringWriter writer = new StringWriter();
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.writeValue(writer, s);
//        System.out.println(writer.toString());
//        objectMapper.writeValue(writer, f);
//        System.out.println(writer.toString());
    }

    public static Object convertOneToAnother(Object one, Class resultClassObject) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        StringWriter writer = new StringWriter();
        objectMapper.writeValue(writer, one);
        String jsonString = writer.toString();
        String[] arrayJsonString = jsonString.split(",");
        String[] arrayFirstProperty = arrayJsonString[0].split("\"");
        arrayFirstProperty[3] = resultClassObject.getSimpleName().toLowerCase();
        StringBuilder sb = new StringBuilder();
        for (String str : arrayFirstProperty) {
            sb.append(str).append("\"");
        }
        for (int i = 1; i < arrayJsonString.length; i++) {
            sb.append(",").append(arrayJsonString[i]);
        }
        jsonString = sb.toString();
        return objectMapper.readValue(jsonString, resultClassObject);
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "className")
    @JsonSubTypes(@JsonSubTypes.Type(value = First.class, name = "first"))
    public static class First {
        public int i;
        public String name;
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "className")
    @JsonSubTypes(@JsonSubTypes.Type(value = Second.class, name = "second"))
    public static class Second {
        public int i;
        public String name;
    }
}
