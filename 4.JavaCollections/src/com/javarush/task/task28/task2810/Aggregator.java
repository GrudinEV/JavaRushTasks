package com.javarush.task.task28.task2810;


import com.javarush.task.task28.task2810.model.*;
import com.javarush.task.task28.task2810.view.HtmlView;
import com.javarush.task.task28.task2810.view.View;

public class Aggregator {
    public static void main(String[] args) {
        Strategy moikrugStrategy = new MoikrugStrategy();
        Provider moikrugProvider = new Provider(moikrugStrategy);
        Strategy hHStrategy = new HHStrategy();
        Provider hHProvider = new Provider(hHStrategy);
        View view = new HtmlView();
        Model model = new Model(view, hHProvider, moikrugProvider);
        Controller controller = new Controller(model);
        view.setController(controller);
        ((HtmlView) view).userCitySelectEmulationMethod();
    }
}
