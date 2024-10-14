package lab2;

import lab2.Candy.Candy;
import org.junit.Test;
import static org.junit.Assert.*;

public class CandyTest {

    @Test
    public void testCandyBuilder() {
        Candy candy = new Candy.CandyBuilder("C1", "Chocolate")
                .withEnergy(250.0f)
                .withProduction("Factory A")
                .withSweetType("Chocolate")
                .withWater(10)
                .withSugar(500)
                .withFructose(100)
                .withVanillin(5)
                .withChocolateType("Dark")
                .withProteins(5.0f)
                .withFats(15.0f)
                .withCarbohydrates(30.0f)
                .build();

        assertEquals("C1", candy.getId());
        assertEquals("Chocolate", candy.getName());
        assertEquals(250.0f, candy.getEnergy(), 0.01);
        assertEquals("Factory A", candy.getProduction());
        assertEquals("Chocolate", candy.getSweetType());
        assertEquals(10, candy.getWater());
        assertEquals(500, candy.getSugar());
        assertEquals(100, candy.getFructose());
        assertEquals(5, candy.getVanillin());
        assertEquals("Dark", candy.getChocolateType());
        assertEquals(5.0f, candy.getProteins(), 0.01);
        assertEquals(15.0f, candy.getFats(), 0.01);
        assertEquals(30.0f, candy.getCarbohydrates(), 0.01);
    }
}
