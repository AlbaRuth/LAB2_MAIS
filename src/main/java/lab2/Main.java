package lab2;

import lab2.Candy.Candy;
import lab2.Parsers.DOMCandyParser;
import lab2.Parsers.SAXCandyParser;
import lab2.Parsers.STAXCandyParser;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        String xmlFilePath = "C:\\Users\\a.sakovich\\Desktop\\study\\LAB2_MAIS\\src\\main\\resources\\candies.xml";

        // DOM парсинг
        DOMCandyParser domParser = new DOMCandyParser();
        List<Candy> candiesFromDOM = domParser.parse(xmlFilePath);
        System.out.println("Candies parsed with DOM:");
        candiesFromDOM.forEach(System.out::println);

        // SAX парсинг
        SAXCandyParser saxParser = new SAXCandyParser();
        List<Candy> candiesFromSAX = saxParser.parse(xmlFilePath);
        System.out.println("Candies parsed with SAX:");
        candiesFromSAX.forEach(System.out::println);

        // STAX парсинг
        STAXCandyParser staxParser = new STAXCandyParser();
        List<Candy> candiesFromSTAX = staxParser.parse(xmlFilePath);
        System.out.println("Candies parsed with STAX:");
        candiesFromSTAX.forEach(System.out::println);
    }
}
