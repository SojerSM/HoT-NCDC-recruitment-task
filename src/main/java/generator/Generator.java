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
    private final int MAX_BLOCKS = 15000000;

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
     * It should imitate collection of blocks and instructions with given
     * edge cases, allowing to test various scenarios in order to fit into
     * maximal required program execution time.
     *
     * @param blocksAmount describe the expected size of a generated list.
     */
    public void populateList(int blocksAmount) {
        List<Integer> blueprints = new ArrayList<>();

        // populate local list of possible blueprints indexes (0 means box-related)
        for (int i = 0; i < MAX_BLUEPRINTS_AMOUNT; i++) {
            blueprints.add(0);
        }

        if (blocksAmount > MAX_BLOCKS || blocksAmount < 0) {
            blocksAmount = 1;
        }

        // populate main list of blocks
        for (int i = 0; i < blocksAmount; i++) {

            int index = 0;
            if (blueprints.size() != 0) {
                index = random.nextInt(blueprints.size());
                blueprints.set(index, blueprints.get(index) + 1);
            }

            // check if random blueprint already contains maximal number of blocks
            if (blueprints.get(index) >= MAX_BLOCKS_PER_BLUEPRINT && index != 0) {
                blueprints.remove(index);
            }

            // make sure the following indexes would be generated with mock ascending order
            if (index > i ) {
                index = random.nextInt(i + 1);
            }
            blocks.add(index + ":" + Characters.generateCode() + "\r\n");
        }
    }

    /**
     * This method is used to write previously generated list to the
     * .txt file located in 'resources' folder.
     *
     */
    public void writeToTheFile() {
        try (FileWriter fw = new FileWriter("src/main/java/mock.txt");
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
