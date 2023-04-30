import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

        Throwable exception = assertThrows(NumberFormatException.class, () -> Bricks.validateInput(parts));
        String expectedMessage = "For input string: \"A\"";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    public void testValidateInputIndexNegative() {
        parts = new String[]{"-1","AAAA"};
        assertFalse(Bricks.validateInput(parts));
    }

    @Test
    public void testValidateInputIllegalDigit() {
        parts = new String[]{"1","ABCY"};
        assertFalse(Bricks.validateInput(parts));
    }
}