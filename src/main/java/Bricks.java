import generator.Generator;
import model.Box;

import java.util.*;

/**
 * NCDC House of Talents recruitment task.
 * If doesn't compile, try to restart your computer. That's weird, works on my PC though...
 *
 * @author Sebastian Mazur
 * @version 0.0.2
 * @since 2023-04-27
 */
public class Bricks {
    private static final String BAD_INPUT_MESSAGE = "klops";
    private static final Scanner scanner = new Scanner(System.in);
    private static final Generator generator = new Generator();

    public static void main(String[] args) {
        generateBlocksList(50);
        Box box = new Box(readFileFromMethodArg());

        if (box.getBlocks() == null) {
            System.out.println(BAD_INPUT_MESSAGE);
        }
        box.printBlocks();
    }

    /**
     * This method takes the .txt file given from the main method
     * argument and transform it into hashMap, where keys are
     * just ascending numbers and values are lines written as a string.
     *
     * @return a hashmap.
     */
    private static Map<Integer, String> readFileFromMethodArg() {
        Map<Integer, String> transformed = new HashMap<>();
        int i = 0;

        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();

            // if given input doesn't meet requirements break the loop and return null
            if (!validateInput(line)) return null;

            transformed.put(i,line);
            i++;
        }

        scanner.close();
        return transformed;
    }

    /**
     * This method checks conditions for given input.
     *
     * @param line stands for string built with blueprint index which should be a positive
     *             natural number and block code which should be all uppercase and exactly
     *             4 characters long.
     * @return true if all conditions are meet, false if input is wrong in any way.
     */
    private static boolean validateInput(String line) {
        String[] parts = line.split(":");

        if (parts[1].length() != 4) return false;

        if (!isUpperCase(parts[1])) return false;

        try {
            Integer.parseInt(parts[0]);
        } catch(NumberFormatException err) {
            return false;
        }
        return true;
    }

    private static boolean isUpperCase(String code) {
        char[] codeArr = code.toCharArray();

        for (int i = 0; i < codeArr.length; i++) {
            if (!Character.isUpperCase(codeArr[i])) return false;
        }
        return true;
    }

    // manual testing purposes
    public static void generateBlocksList(int listSize) {
        generator.populateList(listSize);
        generator.writeToTheFile();
    }
}
