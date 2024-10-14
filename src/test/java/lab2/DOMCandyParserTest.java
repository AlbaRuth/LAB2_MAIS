package lab2;

import lab2.Candy.Candy;
import lab2.Parsers.DOMCandyParser;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

public class DOMCandyParserTest {

    @Test
    public void testParseValidXML() {
        DOMCandyParser parser = new DOMCandyParser();
        List<Candy> candies = parser.parse("C:\\Users\\a.sakovich\\Desktop\\study\\LAB2_MAIS\\src\\main\\resources\\candies.xml"); // Укажите путь к вашему XML файлу

        assertNotNull(candies);
        assertFalse(candies.isEmpty());
        assertEquals(16, candies.size()); // Проверка на количество конфет
        assertEquals("C1", candies.get(0).getId()); // Проверка данных первой конфеты
        assertEquals("Choco Delight", candies.get(0).getName());
    }

    @Test
    public void testParseInvalidXML() {
        DOMCandyParser parser = new DOMCandyParser();
        List<Candy> candies = parser.parse("C:\\Users\\a.sakovich\\Desktop\\study\\LAB2_MAIS\\src\\main\\resources\\test.xml"); // Укажите путь к невалидному XML файлу

        assertNotNull(candies);
        assertTrue(candies.isEmpty()); // Проверка, что возвращается пустой список
    }

    @Test
    public void testParseEmptyXML() {
        DOMCandyParser parser = new DOMCandyParser();
        List<Candy> candies = parser.parse("path/to/empty_candies.xml"); // Укажите путь к пустому XML файлу

        assertNotNull(candies);
        assertTrue(candies.isEmpty()); // Проверка, что возвращается пустой список
    }
}
