package lab2.Parsers;

import lab2.Candy.Candy;
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
    private List<Candy> candies = new ArrayList<>();
    private Candy.CandyBuilder currentCandyBuilder;
    private String currentElement;

    public List<Candy> parse(String xmlFilePath) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(xmlFilePath, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return candies;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("Candy")) {
            currentCandyBuilder = new Candy.CandyBuilder(attributes.getValue("Id"), attributes.getValue("Name"))
                    .withEnergy(Float.parseFloat(attributes.getValue("Energy")))
                    .withProduction(attributes.getValue("Production"));
        }
        currentElement = qName;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String data = new String(ch, start, length).trim();

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
        if (qName.equalsIgnoreCase("Candy")) {
            candies.add(currentCandyBuilder.build());
        }
        currentElement = "";
    }
}
