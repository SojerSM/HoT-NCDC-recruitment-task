package generator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CharactersTest {

    private Characters characters;
    private String code;

    @BeforeEach
    public void setUp() {
        code = Characters.generateCode();
    }

    @Test
    public void testGenerateValidCodeLength() {
        assertEquals(4, code.length());
    }

    @Test
    public void testGeneratedCodeAllUpperCase() {
        boolean flag = true;

        for (int i = 0; i < code.length(); i++) {
            if (!Character.isUpperCase(code.charAt(i))) {
                flag = false;
            }
        }
        assertTrue(flag);
    }
}