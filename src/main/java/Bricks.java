import helpers.Generator;
import helpers.Validator;
import model.Box;

import java.util.*;

/**
 * NCDC House of Talents recruitment task.
 * Doesn't compile? Have you tried to restart your computer?
 *
 * @author Sebastian Mazur
 * @version 0.6.1
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

            if (line.length() > 0 && !line.contains(":")) return null;

            String[] parts = line.split(":");
            if (parts[0].length() > 0) {

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
     * This method check all blueprints in required order; blueprints with id
     * divisible by 3 with no reminder go first and after that the others are
     * processed in default sequence.
     *
     * @param initialized box object with ArrayList of non-assigned blocks and
     *                    HashMap of blueprints.
     */
    private static void checkBlueprints(Box box) {
        List<String> tempAvailableBlocks;
        int usedBlocks;
        int size;

        for (Map.Entry<Integer, List<String>> blueprint : box.getBlueprints().entrySet()) {
            usedBlocks = 0;
            tempAvailableBlocks = new ArrayList<>(box.getBlocks());

            if ((isFirstInvoke && blueprint.getKey() % FIRST_STAGE_DIVIDER == 0)
                    || (!isFirstInvoke && blueprint.getKey() % FIRST_STAGE_DIVIDER != 0)) {

                for (String requiredBlock : blueprint.getValue()) {

                    // temporarily remove block identical to required from the list
                    if (tempAvailableBlocks.contains(requiredBlock)) {
                        tempAvailableBlocks.remove(requiredBlock);
                        usedBlocks++;
                    }
                }
                size = blueprint.getValue().size();
                updateResults(usedBlocks,size,box,tempAvailableBlocks);
            }
        }
        box.setNotUsedBlocks(box.getBlocks().size());
        isFirstInvoke = false;
    }

    /**
     * This method updates specific Box fields regards to success or failure of
     * blueprint checking.
     *
     * @param usedBlocks blocks required for given blueprint creation that were
     *                   found in box.
     * @param size amount of blocks required for given blueprint.
     * @param box box object with HashMap of blueprints that is iterated in the
     *            higher method.
     * @param tempAvailableBlocks copy of Box List of non-assigned blocks reduced
     *                            by currently used blocks.
     */
    private static void updateResults(int usedBlocks, int size, Box box, List<String> tempAvailableBlocks) {
        if (usedBlocks == size) {
            box.setBlocks(tempAvailableBlocks);
            box.setFinishedBlueprints(box.getFinishedBlueprints() + 1);
            if (isFirstInvoke) {
                box.setFirstStageBlocks(box.getFirstStageBlocks() + usedBlocks);
            } else {
                box.setSecondStageBlocks(box.getSecondStageBlocks() + usedBlocks);
            }
        } else {
            box.setNotFinishedBlueprints(box.getNotFinishedBlueprints() + 1);
            box.setMissedBlocks(box.getMissedBlocks() + (size - usedBlocks));
        }
    }

    // manual testing
    public static void generateBlocksList(int listSize) {
        generator.populateList(listSize);
        generator.writeToTheFile();
    }
}
