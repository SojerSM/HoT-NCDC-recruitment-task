package helpers;

/**
 * Class responsible for validating .txt file given as Bricks.java main method argument.
 */
public class Validator {

    private static final int CORRECT_CODE_LENGTH = 4;

    /**
     * This method checks conditions for given input.
     *
     * @param parts stands for string array filled with blueprint index which should be a positive
     *             natural number and block code which should be all uppercase and exactly
     *             4 characters long.
     * @return true if all conditions are meet, false if input is wrong in any way.
     */
    public static boolean validateInput(String[] parts) {
        if (parts[1].length() != CORRECT_CODE_LENGTH) return false;

        if (!checkIsUpperCase(parts[1])) return false;

        if (!checkHasCorrectCharacters(parts[1])) return false;

        if (Integer.parseInt(parts[0]) < 0) return false;

        try {
            Integer.parseInt(parts[0]);
        } catch(NumberFormatException err) {
            return false;
        }
        return true;
    }

    private static boolean checkHasCorrectCharacters(String code) {
        char[] codeArr = code.toCharArray();
        int counter = 0;

        for (char c : codeArr) {
            for(Characters enumChar : Characters.values()) {
                if (String.valueOf(c).equals(enumChar.name())) {
                    counter++;
                }
            }
        }
        return (counter == CORRECT_CODE_LENGTH);
    }

    private static boolean checkIsUpperCase(String code) {
        char[] codeArr = code.toCharArray();

        for (char c : codeArr) {
            if (!Character.isUpperCase(c)) return false;
        }
        return true;
    }
}
