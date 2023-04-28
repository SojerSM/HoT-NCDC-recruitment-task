import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BricksTest {

    private String[] parts;

    @Test
    public void testValidateCorrectInput() {
        parts = new String[]{"1","AAAA"};
        assertTrue(Bricks.validateInput(parts));
    }

    @Test
    public void testValidateInputCodeTooLong() {
        parts = new String[]{"1","AAAAA"};
        assertFalse(Bricks.validateInput(parts));
    }

    @Test
    public void testValidateInputCodeTooShort() {
        parts = new String[]{"1","AAA"};
        assertFalse(Bricks.validateInput(parts));
    }

    @Test
    public void testValidateInputCodeNotUpperCase() {
        parts = new String[]{"1","AaAa"};
        assertFalse(Bricks.validateInput(parts));
    }

    @Test
    public void testValidateInputIndexNotANumber() {
        parts = new String[]{"A","AAAA"};
        assertFalse(Bricks.validateInput(parts));
    }

    @Test
    public void testValidateInputIndexNegative() {
        parts = new String[]{"-1","AAAA"};
        assertFalse(Bricks.validateInput(parts));
    }
}