package lab2.Parsers;

import lab2.Candy.Candy;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class DOMCandyParser {

    private static final Logger logger = LogManager.getLogger(DOMCandyParser.class);

    public List<Candy> parse(String xmlFilePath) {
        List<Candy> candies = new ArrayList<>();
        logger.info("Начало парсинга файла: {}", xmlFilePath);
        try {
            File inputFile = new File(xmlFilePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            NodeList candyList = doc.getElementsByTagName("Candy");
            logger.debug("Найдено {} элементов Candy", candyList.getLength());

            for (int i = 0; i < candyList.getLength(); i++) {
                Node node = candyList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element candyElement = (Element) node;

                    Candy.CandyBuilder builder = new Candy.CandyBuilder(
                            candyElement.getAttribute("Id"),
                            candyElement.getAttribute("Name")
                    );

                    builder.withEnergy(Float.parseFloat(candyElement.getAttribute("Energy")))
                            .withProduction(candyElement.getAttribute("Production"));

                    Element sweetElement = (Element) candyElement.getElementsByTagName("Sweet").item(0);
                    builder.withSweetType(sweetElement.getTextContent());

                    Element ingredientsElement = (Element) candyElement.getElementsByTagName("Ingredients").item(0);
                    builder.withWater(Integer.parseInt(ingredientsElement.getElementsByTagName("Water").item(0).getTextContent()))
                            .withSugar(Integer.parseInt(ingredientsElement.getElementsByTagName("Sugar").item(0).getTextContent()))
                            .withFructose(Integer.parseInt(ingredientsElement.getElementsByTagName("Fructose").item(0).getTextContent()));

                    if (ingredientsElement.getElementsByTagName("Vanillin").getLength() > 0) {
                        builder.withVanillin(Integer.parseInt(ingredientsElement.getElementsByTagName("Vanillin").item(0).getTextContent()));
                    }

                    if (ingredientsElement.getElementsByTagName("ChocolateType").getLength() > 0) {
                        builder.withChocolateType(ingredientsElement.getElementsByTagName("ChocolateType").item(0).getTextContent());
                    }

                    Element valueElement = (Element) candyElement.getElementsByTagName("Value").item(0);
                    builder.withProteins(Float.parseFloat(valueElement.getElementsByTagName("Proteins").item(0).getTextContent()))
                            .withFats(Float.parseFloat(valueElement.getElementsByTagName("Fats").item(0).getTextContent()))
                            .withCarbohydrates(Float.parseFloat(valueElement.getElementsByTagName("Carbohydrates").item(0).getTextContent()));

                    candies.add(builder.build());
                }
            }
            logger.info("Парсинг завершен, найдено {} конфет", candies.size());
        } catch (Exception e) {
            logger.error("Ошибка при парсинге XML файла: {}", xmlFilePath, e);
        }
        return candies;
    }
}
