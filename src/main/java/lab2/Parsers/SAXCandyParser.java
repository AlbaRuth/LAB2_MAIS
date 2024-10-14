package lab2.Parsers;

import lab2.Candy.Candy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.ArrayList;
import java.util.List;

public class SAXCandyParser extends DefaultHandler {

    private static final Logger logger = LogManager.getLogger(SAXCandyParser.class);

    private List<Candy> candies = new ArrayList<>();
    private Candy.CandyBuilder currentCandyBuilder;
    private String currentElement;

    public List<Candy> parse(String xmlFilePath) {
        logger.info("Начало парсинга файла: {}", xmlFilePath);
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(xmlFilePath, this);
            logger.info("Парсинг завершен, найдено {} конфет", candies.size());
        } catch (Exception e) {
            logger.error("Ошибка при парсинге XML файла: {}", xmlFilePath, e);
        }
        return candies;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("Candy")) {
            logger.debug("Начало обработки элемента: {}", qName);
            currentCandyBuilder = new Candy.CandyBuilder(attributes.getValue("Id"), attributes.getValue("Name"))
                    .withEnergy(Float.parseFloat(attributes.getValue("Energy")))
                    .withProduction(attributes.getValue("Production"));
            logger.debug("Создание Candy с id: {}", attributes.getValue("Id"));
        }
        currentElement = qName;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String data = new String(ch, start, length).trim();
        logger.debug("Считаны данные для элемента {}: {}", currentElement, data);
        if (currentCandyBuilder != null) {
            switch (currentElement) {
                case "Sweet":
                    currentCandyBuilder.withSweetType(data);
                    break;
                case "Water":
                    currentCandyBuilder.withWater(Integer.parseInt(data));
                    break;
                case "Sugar":
                    currentCandyBuilder.withSugar(Integer.parseInt(data));
                    break;
                case "Fructose":
                    currentCandyBuilder.withFructose(Integer.parseInt(data));
                    break;
                case "Vanillin":
                    currentCandyBuilder.withVanillin(Integer.parseInt(data));
                    break;
                case "ChocolateType":
                    currentCandyBuilder.withChocolateType(data);
                    break;
                case "Proteins":
                    currentCandyBuilder.withProteins(Float.parseFloat(data));
                    break;
                case "Fats":
                    currentCandyBuilder.withFats(Float.parseFloat(data));
                    break;
                case "Carbohydrates":
                    currentCandyBuilder.withCarbohydrates(Float.parseFloat(data));
                    break;
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        logger.debug("Завершение обработки элемента: {}", qName);
        if (qName.equalsIgnoreCase("Candy")) {
            candies.add(currentCandyBuilder.build());
            logger.info("Конфета добавлена: {}", currentCandyBuilder.toString());
        }
        currentElement = "";
    }
}
