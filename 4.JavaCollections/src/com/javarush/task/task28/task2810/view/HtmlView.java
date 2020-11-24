package com.javarush.task.task28.task2810.view;

import com.javarush.task.task28.task2810.Controller;
import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.*;
import java.util.List;

public class HtmlView implements View{
    private Controller controller;
    private final String filePath = "./4.JavaCollections/src/" + this.getClass().getPackage().getName().replace(".", "/") + "/vacancies.html";

    @Override
    public void update(List<Vacancy> vacancies) {
        try {
            updateFile(getUpdatedFileContent(vacancies));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void userCitySelectEmulationMethod() {
        controller.onCitySelect("пермь");
    }

    private String getUpdatedFileContent(List<Vacancy> vacancyList) {
        Document doc = null;
        try {
            doc = getDocument();
            Element template = doc.getElementsByClass("template").first();
            Element templateCopy = template.clone();
            templateCopy.removeClass("template");
            templateCopy.removeAttr("style");
            doc.select("tr[class=vacancy]").remove();
            for (Vacancy vacancy : vacancyList) {
                Element vacancyInTemplate = templateCopy.clone();
                Element title = vacancyInTemplate.getElementsByTag("a").first();
                title.text(vacancy.getTitle());
                title.attr("href", vacancy.getUrl());
                vacancyInTemplate.getElementsByAttributeValue("class", "city").first().text(vacancy.getCity());
                vacancyInTemplate.getElementsByAttributeValue("class", "companyName").first().text(vacancy.getCompanyName());
                vacancyInTemplate.getElementsByAttributeValue("class", "salary").first().text(vacancy.getSalary());
                template.before(vacancyInTemplate);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Some exception occurred";
        }
        return doc != null ? doc.toString() : "";
    }

    private void updateFile(String data) throws IOException {
        if (data != null) {
            try (PrintWriter pw = new PrintWriter(filePath, "UTF-8");
                 StringReader sr = new StringReader(data)){
                int b;
                while ((b = sr.read()) > -1) {
                    pw.write(b);
                }
                pw.flush();
            }
        }
    }

    protected Document getDocument() throws IOException{
        return Jsoup.parse(new File(filePath), "UTF-8");
    }
}
