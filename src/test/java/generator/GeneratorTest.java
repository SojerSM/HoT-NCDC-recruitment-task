package generator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GeneratorTest {

    private Generator generator;

    @BeforeEach
    public void setUp() {
        this.generator = new Generator();
    }

    @Test
    public void testPopulateWithOneHundredCodes() {
        generator.populateList(100);
        assertEquals(100, generator.getBlocks().size());
    }

    @Test
    public void testPopulateWithNegativeCodesAmount() {
        generator.populateList(-1);
        assertEquals(1, generator.getBlocks().size());
    }

    @Test
    public void testPopulateWithMoreThanFifteenMillionsCodes() {
        generator.populateList(15000001);
        assertEquals(1,generator.getBlocks().size());
    }
}