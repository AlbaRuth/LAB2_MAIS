package lab2;

import lab2.Candy.Candy;
import lab2.Parsers.SAXCandyParser;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

public class SAXCandyParserTest {

    @Test
    public void testParseValidXML() {
        SAXCandyParser parser = new SAXCandyParser();
        List<Candy> candies = parser.parse("path/to/valid_candies.xml"); // Укажите путь к вашему XML файлу

        assertNotNull(candies);
        assertFalse(candies.isEmpty());
        assertEquals(10, candies.size()); // Проверка на количество конфет
        assertEquals("C1", candies.get(0).getId()); // Проверка данных первой конфеты
        assertEquals("Chocolate", candies.get(0).getName());
    }

    @Test
    public void testParseInvalidXML() {
        SAXCandyParser parser = new SAXCandyParser();
        List<Candy> candies = parser.parse("path/to/invalid_candies.xml"); // Укажите путь к невалидному XML файлу

        assertNotNull(candies);
        assertTrue(candies.isEmpty()); // Проверка, что возвращается пустой список
    }

    @Test
    public void testParseEmptyXML() {
        SAXCandyParser parser = new SAXCandyParser();
        List<Candy> candies = parser.parse("path/to/empty_candies.xml"); // Укажите путь к пустому XML файлу

        assertNotNull(candies);
        assertTrue(candies.isEmpty()); // Проверка, что возвращается пустой список
    }
}
