package generator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Generator {

    private final int MAX_BLOCKS_PER_BLUEPRINT = 5000;
    private final int MAX_BLUEPRINTS_AMOUNT = 1000;
    private final int MAX_BLOCKS = 10000000;

    private static final Random random = new Random();
    private List<String> blocks;

    public Generator() {
        blocks = new ArrayList<>();
    }

    public List<String> getBlocks() {
        return blocks;
    }

    /**
     * This method is used to generate mock list of formatted blocks,
     * built of blueprint number and random code with length of 4 characters.
     *
     * @param blocksAmount describe the size of a generated list.
     */
    public void populateList(int blocksAmount) {
        if (blocksAmount > MAX_BLOCKS || blocksAmount < 0) {
            blocksAmount = 1;
        }

        for (int i = 0; i < blocksAmount; i++) {

            int blueprint = random.nextInt(MAX_BLUEPRINTS_AMOUNT);

            if (blueprint > i ) {
                blueprint = random.nextInt(i + 1);
            }

            blocks.add(blueprint + ":" + Characters.generateCode());
        }
    }

    /**
     * This method is used to write previously generated list to the
     * .txt file located in 'resources' folder.
     *
     */
    public void writeToTheFile() {
        try (FileWriter fw = new FileWriter("src/main/resources/mock.txt");
             BufferedWriter bw = new BufferedWriter(fw)) {

            for (String element: blocks) {
                bw.write(element);
                bw.newLine();
            }
        } catch (IOException error) {
            System.err.format("IOException: %s%n", error);
        }
    }
}
