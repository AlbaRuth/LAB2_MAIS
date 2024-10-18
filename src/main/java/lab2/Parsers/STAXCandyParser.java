package lab2.Parsers;


import lab2.Candy.Candy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class STAXCandyParser {

    private static final Logger logger = LogManager.getLogger(STAXCandyParser.class);

    public List<Candy> parse(String xmlFilePath) {
        List<Candy> candies = new ArrayList<>();
        logger.info("Начало парсинга файла: {}", xmlFilePath);

        Candy.CandyBuilder builder = null;
        String currentElement = "";

        try (FileInputStream fis = new FileInputStream(xmlFilePath)) {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLStreamReader reader = factory.createXMLStreamReader(fis);

            while (reader.hasNext()) {
                int event = reader.next();

                switch (event) {
                    case XMLStreamConstants.START_ELEMENT:
                        currentElement = reader.getLocalName();

                        if ("Candy".equals(currentElement)) {
                            builder = new Candy.CandyBuilder(
                                    reader.getAttributeValue(null, "Id"),
                                    reader.getAttributeValue(null, "Name")
                            );
                            builder.withEnergy(Float.parseFloat(reader.getAttributeValue(null, "Energy")))
                                    .withProduction(reader.getAttributeValue(null, "Production"));
                            logger.debug("Начало парсинга элемента Candy с id: {}", reader.getAttributeValue(null, "Id"));
                        }
                        break;

                    case XMLStreamConstants.CHARACTERS:
                        String data = reader.getText().trim();

                        if (builder != null && !data.isEmpty()) {
                            switch (currentElement) {
                                case "Sweet":
                                    builder.withSweetType(data);
                                    break;
                                case "Water":
                                    builder.withWater(Integer.parseInt(data));
                                    break;
                                case "Sugar":
                                    builder.withSugar(Integer.parseInt(data));
                                    break;
                                case "Fructose":
                                    builder.withFructose(Integer.parseInt(data));
                                    break;
                                case "Vanillin":
                                    builder.withVanillin(Integer.parseInt(data));
                                    break;
                                case "ChocolateType":
                                    builder.withChocolateType(data);
                                    break;
                                case "Proteins":
                                    builder.withProteins(Float.parseFloat(data));
                                    break;
                                case "Fats":
                                    builder.withFats(Float.parseFloat(data));
                                    break;
                                case "Carbohydrates":
                                    builder.withCarbohydrates(Float.parseFloat(data));
                                    break;
                            }
                        }
                        break;

                    case XMLStreamConstants.END_ELEMENT:
                        currentElement = reader.getLocalName();
                        if ("Candy".equals(currentElement) && builder != null) {
                            candies.add(builder.build());
                            logger.debug("Конфета добавлена в список: {}", builder.build().getName());
                        }
                        break;
                }
            }
            logger.info("Парсинг завершен, найдено {} конфет", candies.size());

        } catch (XMLStreamException | NumberFormatException e) {
            logger.error("Ошибка при парсинге XML файла: {}", xmlFilePath, e);
        } catch (Exception e) {
            logger.error("Неизвестная ошибка: {}", xmlFilePath, e);
        }

        return candies;
    }
}
