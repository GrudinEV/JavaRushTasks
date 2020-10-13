package com.javarush.task.task33.task3305;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import static com.fasterxml.jackson.annotation.JsonInclude.*;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "className")
@JsonInclude(Include.NON_DEFAULT)
public abstract class Vehicle {
    protected String name;
    protected String owner;
    protected int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Vehicle() {
    }
}