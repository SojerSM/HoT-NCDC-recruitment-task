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
    private static final Scanner scanner = new Scanner(System.in);
    private static final Generator generator = new Generator();

    public static void main(String[] args) {

//        generateBlocksList(20);
        Box box = new Box(readFileFromMethodArg());

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
    public static Map<Integer, String[]> readFileFromMethodArg() {
        Map<Integer, String[]> transformed = new HashMap<>();
        int i = 0;

        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(":");
            transformed.put(i,parts);
            i++;
        }
        scanner.close();
        return transformed;
    }
}
