package com.javarush.task.task14.task1408;


public abstract class Hen {
    
//    Country country;
        
    abstract int getCountOfEggsPerMonth();
        
    String getDescription() {
        return "Я - курица.";
    }
}