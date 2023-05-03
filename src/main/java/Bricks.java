import helpers.Generator;
import helpers.Validator;
import model.Box;

import java.util.*;

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
    private static final int FIRST_STAGE_DIVIDER = 3;
    private static final Scanner scanner = new Scanner(System.in);
    private static final Generator generator = new Generator();
    private static boolean isFirstInvoke = true;

    public static void main(String[] args) {
        Box box = new Box();

        try {
            box.sortBlocksByBlueprint(readFileFromMethodArg());
        } catch (NullPointerException err) {
            System.out.println(BAD_INPUT_MESSAGE);
            return;
        }

        for (int i = 0; i < 2; i++) {
            checkBlueprints(box);
        }

        box.printResults();
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
        return transformed;
    }

    /**
     * This method runs the first stage of program requirements.
     *
     * @param initialized box object.
     */
    private static void checkBlueprints(Box box) {
        List<String> tempAvailableBlocks;
        int usedBlocks;

        for (Map.Entry<Integer, List<String>> blueprint : box.getBlueprints().entrySet()) {
            usedBlocks = 0;
            tempAvailableBlocks = new ArrayList<>(box.getBlocks());

            if ((isFirstInvoke && blueprint.getKey() % FIRST_STAGE_DIVIDER == 0)
                    || (!isFirstInvoke && blueprint.getKey() % FIRST_STAGE_DIVIDER != 0)) {

                for (String requiredBlock : blueprint.getValue()) {

                    if (tempAvailableBlocks.contains(requiredBlock)) {
                        tempAvailableBlocks.remove(requiredBlock);
                        usedBlocks++;
                    }
                }

                if (usedBlocks == blueprint.getValue().size()) {
                    box.setBlocks(tempAvailableBlocks);
                    box.setFinishedBlueprints(box.getFinishedBlueprints() + 1);
                    if (isFirstInvoke) {
                        box.setFirstStageBlocks(box.getFirstStageBlocks() + usedBlocks);
                    } else {
                        box.setSecondStageBlocks(box.getSecondStageBlocks() + usedBlocks);
                    }
                } else {
                    box.setNotFinishedBlueprints(box.getNotFinishedBlueprints() + 1);
                    box.setMissedBlocks(box.getMissedBlocks() + (blueprint.getValue().size() - usedBlocks));
                }
            }
        }
        isFirstInvoke = false;
        box.setNotUsedBlocks(box.getBlocks().size());
    }

    // manual testing purposes
    public static void generateBlocksList(int listSize) {
        generator.populateList(listSize);
        generator.writeToTheFile();
    }
}
