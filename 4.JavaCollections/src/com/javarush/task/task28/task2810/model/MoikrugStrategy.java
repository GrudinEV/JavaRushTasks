package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class MoikrugStrategy implements Strategy{
    private static final String URL_FORMAT = "https://moikrug.ru/vacancies?q=java+%s&page=%d";

    @Override
    public List<Vacancy> getVacancies(String searchString) {
        List<Vacancy> listVacancies = new ArrayList<>();
        int PAGE_VALUE = 0;
        Document document = null;
        try {
            while (true) {
                document = getDocument(searchString, PAGE_VALUE);
                Elements elements = document.getElementsByClass("job");
                if (elements.size() == 0) {
                    break;
                }
                for (Element element : elements) {
                    if (element != null) {
                        Vacancy vac = new Vacancy();
                        vac.setTitle(element.select("div[class=title]").first().text());
                        Element meta = element.select("div[class=vacancy-card__meta]").first();
                        vac.setCompanyName(element.select("div[class=company_name]").first().text());
                        Element cityElement = element.select("span[class=location]").first();
                        if (cityElement != null) {
                            vac.setCity(cityElement.text());
                        } else {
                            vac.setCity("");
                        }
                        vac.setSiteName("https://moikrug.ru/");
                        String urlPage = "https://moikrug.ru" + element.select("div[class=title]").first().select("a").first().attr("href");
                        vac.setUrl(urlPage);
                        Element salaryElement = element.select("div[class=salary]").first();
                        if (salaryElement != null) {
                            vac.setSalary(salaryElement.text());
                        } else {
                            vac.setSalary("");
                        }
                        listVacancies.add(vac);
                    }
                }
//                Elements elements = document.select("li[class^=card-list__item]");
//                if (elements.size() == 0) {
//                    break;
//                }
//                for (Element element : elements) {
//                    if (element != null) {
//                        Vacancy vac = new Vacancy();
//                        vac.setTitle(element.select("div[class=vacancy-card__title]").first().select("a").first().text());
//                        Element meta = element.select("div[class=vacancy-card__meta]").first();
//                        vac.setCompanyName(meta.getElementsByAttributeValueStarting("href", "/companies/").first().text());
//                        Element cityElement = meta.getElementsByAttributeValueContaining("href", "city").first();
//                        if (cityElement != null) {
//                            vac.setCity(cityElement.text());
//                        } else {
//                            vac.setCity("");
//                        }
//                        vac.setSiteName("https://moikrug.ru/");
//                        String urlPage = "https://moikrug.ru" + element.select("div[class=vacancy-card__title]").first().select("a").first().attr("href");
//                        vac.setUrl(urlPage);
//                        Element salaryElement = element.select("div[class=basic-salary]").first();
//                        if (salaryElement != null) {
//                            vac.setSalary(salaryElement.text());
//                        } else {
//                            vac.setSalary("");
//                        }
//                        listVacancies.add(vac);
//                    }
//                }
                ++PAGE_VALUE;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listVacancies;
    }

    protected Document getDocument(String searchString, int page) throws IOException{
        searchString = String.format(URL_FORMAT, URLEncoder.encode(searchString, "UTF-8"), page);
        Connection connection = Jsoup.connect(searchString).userAgent("Mozilla/5.0 (jsoup)").referrer("");
        return connection.get();
    }
}
