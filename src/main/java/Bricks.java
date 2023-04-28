import generator.Generator;
import model.Box;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

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
//        generateBlocksList(20);
        Box box = new Box(readFileFromMethodArg(args));

        if (box.getBlocks() == null) {
            System.out.println(BAD_INPUT_MESSAGE);
            return;
        }

        for (Map.Entry<Integer, String[]> entry : box.getBlocks().entrySet()) {
            Integer key = entry.getKey();
            String[] values = entry.getValue();

            System.out.println(key + ": " + values[0] + " " + values[1]);
        }
    }

    public static void generateBlocksList(int listSize) {
        generator.populateList(listSize);
        generator.writeToTheFile();
    }

    /**
     * This method takes the .txt file given from the main method
     * argument and transform it into hashMap, where keys are
     * just ascending numbers and values are two element arrays
     * of type String.
     *
     * @return a hashmap.
     */
    private static Map<Integer, String[]> readFileFromMethodArg(String[] args) {
        Map<Integer, String[]> transformed = new HashMap<>();
        int i = 0;

        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(":");

            // if given input doesn't meet requirements break the loop and return null;
            if (!validateInput(parts)) return null;

            transformed.put(i,parts);
            i++;
        }
        scanner.close();
        return transformed;
    }

    /**
     * This method checks conditions for given input.
     *
     * @param parts stands for blueprint index which should be a positive natural number
     *              and block code which should be all uppercase and exactly 4 characters
     *              long.
     * @return true if all conditions are meet, false if input is wrong in any way.
     */
    private static boolean validateInput(String[] parts) {
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
}
