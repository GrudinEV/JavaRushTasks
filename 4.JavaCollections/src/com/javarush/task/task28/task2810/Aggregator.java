package com.javarush.task.task28.task2810;


import com.javarush.task.task28.task2810.model.HHStrategy;
import com.javarush.task.task28.task2810.model.Provider;
import com.javarush.task.task28.task2810.model.Strategy;
import com.javarush.task.task28.task2810.model.Model;
import com.javarush.task.task28.task2810.view.HtmlView;
import com.javarush.task.task28.task2810.view.View;

public class Aggregator {
    public static void main(String[] args) {
        Strategy strategy = new HHStrategy();
        Provider provider = new Provider(strategy);
        View view = new HtmlView();
        Model model = new Model(view, provider);
        Controller controller = new Controller(model);
        view.setController(controller);
        ((HtmlView) view).userCitySelectEmulationMethod();
    }
}
