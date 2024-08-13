package org.example.service;

import lombok.Getter;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.example.model.Currency;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.*;


@Getter
public class Currency_service {
    // ? Currency link
    private static final String CBR_URL = "https://www.cbr.ru/scripts/XML_daily.asp";
    private final List<Currency> currencyList = new ArrayList<>();
    double input_value = -1;
    String curr_1 = "-1";
    String curr_2 = "-1";
    double res_value = -1;



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
                        currencyElement.getElementsByTagName("Value").item(0).getTextContent(),
                        currencyElement.getElementsByTagName("VunitRate").item(0).getTextContent()
                );

                currencyList.add(currency);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void convertCurrency(String currency_1, String currency_2, String input_currency_amount) {
        double valute_1 = -1, valute_2 = -1;

        for (Currency currency : currencyList) {
            if (currency_1.equals(currency.getCharCode())) {
                valute_1 = stringToDouble(currency.getVunitRate());
            }

            if (currency_2.equals(currency.getCharCode())) {
                valute_2 = stringToDouble(currency.getVunitRate());
            }
        }

        input_value = stringToDouble(input_currency_amount);
        curr_1 = currency_1;
        curr_2 = currency_2;
        res_value = input_value * valute_1 / valute_2;
    }

    private double stringToDouble(String input) {
        double doubleValue = 0.0;
        try {
            NumberFormat format = NumberFormat.getInstance(Locale.FRANCE); // Используем локаль, где запятая - разделитель
            Number number = format.parse(input);
            doubleValue = number.doubleValue();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return doubleValue;
    }

}
