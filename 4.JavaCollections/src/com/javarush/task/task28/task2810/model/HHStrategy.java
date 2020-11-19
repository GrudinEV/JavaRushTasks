package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class HHStrategy implements Strategy{
    private static final String URL_FORMAT = "http://hh.ua/search/vacancy?text=java+%s&page=%d";

    @Override
    public List<Vacancy> getVacancies(String searchString) {
        List<Vacancy> listVacancies = new ArrayList<>();
        try {
            for (int i = 0; ; i++){
                Document doc = getDocument(searchString, i);
                Elements vacancyElements = doc.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy");
                if (vacancyElements.size() == 0) {
                    break;
                }
                URL url = new URL(doc.location());
                for (Element element : vacancyElements) {
                    String title = element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-title").first().text();
                    String salary = "";
                    if (element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-compensation").first() != null) {
                        salary = element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-compensation").first().text();
                    }
                    String city = element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-address").first().text();
                    String companyName = element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-employer").first().text().trim();
                    String siteName = url.getHost();
                    String urlString = element.getElementsByAttributeValue("data-qa", "vacancy-serp__vacancy-title").first().attr("href");
                    Vacancy vacancy = new Vacancy();
                    vacancy.setTitle(title);
                    vacancy.setSalary(salary);
                    vacancy.setCity(city);
                    vacancy.setCompanyName(companyName);
                    vacancy.setSiteName(siteName);
                    vacancy.setUrl(urlString);
                    listVacancies.add(vacancy);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listVacancies;
    }

    protected Document getDocument(String searchString, int page) throws IOException{
        searchString = String.format(URL_FORMAT, URLEncoder.encode(searchString, "UTF-8"), page);
        Connection connection = Jsoup.connect(searchString).userAgent("Chrome/86.0.4240.198").referrer("");
        return connection.get();
    }
}
