import helpers.Generator;
import helpers.Validator;
import model.Box;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * NCDC House of Talents recruitment task.
 * If doesn't compile, try to restart your computer. That's weird, works on my PC though...
 *
 * @author Sebastian Mazur
 * @version 0.3.3
 * @since 2023-04-27
 */
public class Bricks {
    private static final String BAD_INPUT_MESSAGE = "klops";
    private static final Scanner scanner = new Scanner(System.in);
    private static final Generator generator = new Generator();

    public static void main(String[] args) {
//      generateBlocksList(20);
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
    private static Map<Integer, List<String>> readFileFromMethodArg() {
        Map<Integer, String> transformed = new HashMap<>();
        int i = 0;

        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            line = line.replaceAll("\\r?\\n", "");

            String[] parts = line.split(":");
            if (line.length() != 0 && parts[0].length() > 0) {

                // if given input doesn't meet requirements break the loop and return null
                if (!Validator.validateInput(parts)) return null;

                transformed.put(i,parts[0].concat(":" + parts[1]));
                i++;
            }
        }
        scanner.close();
        return Box.sortBlocksByBlueprint(transformed);
    }

    // manual testing purposes
    public static void generateBlocksList(int listSize) {
        generator.populateList(listSize);
        generator.writeToTheFile();
    }
}
