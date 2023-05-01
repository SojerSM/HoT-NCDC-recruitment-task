package helpers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {

    private String[] parts;

    @Test
    public void testValidateCorrectInput() {
        parts = new String[]{"1","AAAA"};
        assertTrue(Validator.validateInput(parts));
    }

    @Test
    public void testValidateInputCodeTooLong() {
        parts = new String[]{"1","AAAAA"};
        assertFalse(Validator.validateInput(parts));
    }

    @Test
    public void testValidateInputCodeTooShort() {
        parts = new String[]{"1","AAA"};
        assertFalse(Validator.validateInput(parts));
    }

    @Test
    public void testValidateInputCodeNotUpperCase() {
        parts = new String[]{"1","AaAa"};
        assertFalse(Validator.validateInput(parts));
    }

    @Test
    public void testValidateInputIndexNotANumber() {
        parts = new String[]{"A","AAAA"};

        Throwable exception = assertThrows(NumberFormatException.class, () -> Validator.validateInput(parts));
        String expectedMessage = "For input string: \"A\"";
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    public void testValidateInputIndexNegative() {
        parts = new String[]{"-1","AAAA"};
        assertFalse(Validator.validateInput(parts));
    }

    @Test
    public void testValidateInputIllegalDigit() {
        parts = new String[]{"1","ABCY"};
        assertFalse(Validator.validateInput(parts));
    }

    @Test
    public void testValidateInputAllIllegalDigits() {
        parts = new String[]{"1","@#$%"};
        assertFalse(Validator.validateInput(parts));
    }
}