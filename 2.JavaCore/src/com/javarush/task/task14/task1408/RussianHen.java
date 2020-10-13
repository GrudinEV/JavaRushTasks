package com.javarush.task.task14.task1408;


public class RussianHen extends Hen {
    
//    public RussianHen(Country country) {
//        this.country = country;
//    }
    
    int getCountOfEggsPerMonth() {
        return 5;
    };
        
    String getDescription() {
        return super.getDescription() + " Моя страна - " + Country.RUSSIA + ". Я несу " + this.getCountOfEggsPerMonth() + " яиц в месяц.";
    }
    
}