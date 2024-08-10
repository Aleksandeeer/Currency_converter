package org.example.service;

import lombok.Getter;
import lombok.Setter;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.example.model.Currency;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;


@Getter
public class Currency_service {
    // ? Currency link
    private static final String CBR_URL = "https://www.cbr.ru/scripts/XML_daily.asp";
    private final List<Currency> currencyList = new ArrayList<>();

    public void setCurrentCurrency(){
        try {
            URL url = new URL(CBR_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(connection.getInputStream());

            // Парсинг XML и получение данных
            NodeList nodeCurrency = doc.getElementsByTagName("Valute");

            for (int i = 0; i < nodeCurrency.getLength(); i++) {
                Element currencyElement = (Element) nodeCurrency.item(i);
                Currency currency = new Currency(
                        currencyElement.getAttribute("ID"),
                        currencyElement.getElementsByTagName("CharCode").item(0).getTextContent(),
                        currencyElement.getElementsByTagName("Nominal").item(0).getTextContent(),
                        currencyElement.getElementsByTagName("Name").item(0).getTextContent(),
                        currencyElement.getElementsByTagName("Value").item(0).getTextContent()
                );

                currencyList.add(currency);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
