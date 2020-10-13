package com.javarush.task.task14.task1408;

public class MoldovanHen extends Hen {

    int getCountOfEggsPerMonth() {
        return 5;
    };

    String getDescription() {
        return super.getDescription() + " Моя страна - " + Country.MOLDOVA + ". Я несу " + this.getCountOfEggsPerMonth() + " яиц в месяц.";
    }

}
