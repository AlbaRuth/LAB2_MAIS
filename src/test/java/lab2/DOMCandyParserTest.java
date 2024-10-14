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
        List<Candy> candies = parser.parse("C:\\Users\\a.sakovich\\Desktop\\study\\LAB2_MAIS\\src\\main\\resources\\test.xml");

        assertNotNull(candies);
        assertFalse(candies.isEmpty());
        assertEquals(3, candies.size());
        assertEquals("C1", candies.get(0).getId());
        assertEquals("Choco Delight", candies.get(0).getName());
    }

}
