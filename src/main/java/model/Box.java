package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class responsible for storing collection of blocks and all numbers required to
 * be displayed back as a program result.
 */
public class Box {
    private List<String> blocks;
    private Map<Integer, List<String>> blueprints;
    private int firstStageBlocks;
    private int secondStageBlocks;
    private int notUsedBlocks;
    private int missedBlocks;
    private int finishedBlueprints;
    private int notFinishedBlueprints;

    public Box() {
        this.blocks = new ArrayList<>();
        this.blueprints = new HashMap<>();
        this.firstStageBlocks = 0;
        this.secondStageBlocks = 0;
        this.notUsedBlocks = 0;
        this.missedBlocks = 0;
        this.finishedBlueprints = 0;
        this.notFinishedBlueprints = 0;
    }

    /**
     * This method takes map created in readFileFromMethodArg() inside `Bricks` class,
     * sorts it by blueprints indexes and gets rid of previously existing keys that
     * does not have any matching blueprint index.
     *
     * @param blocks is a map created from .txt file at the program init.
     */
    public void sortBlocksByBlueprint(Map<Integer, String> blocks) {
        Map<Integer, List<String>> orderedBlocks = new HashMap<>();
        int size = blocks.size();

        for (int i = 0; i < size; i++) {
            List<String> relatedCodes = new ArrayList<>();

            // search for blueprint indexes matching the given key, if so, add related code to the list
            for (Map.Entry<Integer, String> block : blocks.entrySet()) {
                String[] parts = block.getValue().split(":");

                if (Integer.parseInt(parts[0]) == i && i > 0) {
                    relatedCodes.add(parts[1]);
                }

                if (Integer.parseInt(parts[0]) == i && i == 0) {
                    this.blocks.add(parts[1]);
                }
            }

            if (!relatedCodes.isEmpty()) {
                orderedBlocks.put(i, relatedCodes);
            }
        }
        this.blueprints = orderedBlocks;
    }

    // manual testing purposes
    public void printBoxContent() {
        System.out.println(this.blocks);
        try {
            for (Map.Entry<Integer, List<String>> entry : this.blueprints.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        } catch(NullPointerException error) {
            System.out.println(error);
        }
    }

    public void printResults() {
        System.out.println(getFirstStageBlocks());
        System.out.println(getSecondStageBlocks());
        System.out.println(getNotUsedBlocks());
        System.out.println(getMissedBlocks());
        System.out.println(getFinishedBlueprints());
        System.out.println(getNotFinishedBlueprints());
    }

    public List<String> getBlocks() {
        return blocks;
    }

    public void setBlocks(List<String> blocks) {
        this.blocks = blocks;
    }

    public Map<Integer, List<String>> getBlueprints() {
        return this.blueprints;
    }

    public int getFirstStageBlocks() {
        return firstStageBlocks;
    }

    public void setFirstStageBlocks(int firstStageBlocks) {
        this.firstStageBlocks = firstStageBlocks;
    }

    public int getSecondStageBlocks() {
        return secondStageBlocks;
    }

    public void setSecondStageBlocks(int secondStageBlocks) {
        this.secondStageBlocks = secondStageBlocks;
    }

    public int getNotUsedBlocks() {
        return notUsedBlocks;
    }

    public void setNotUsedBlocks(int notUsedBlocks) {
        this.notUsedBlocks = notUsedBlocks;
    }

    public int getMissedBlocks() {
        return missedBlocks;
    }

    public void setMissedBlocks(int missedBlocks) {
        this.missedBlocks = missedBlocks;
    }

    public int getFinishedBlueprints() {
        return finishedBlueprints;
    }

    public void setFinishedBlueprints(int finishedBlueprints) {
        this.finishedBlueprints = finishedBlueprints;
    }

    public int getNotFinishedBlueprints() {
        return notFinishedBlueprints;
    }

    public void setNotFinishedBlueprints(int notFinishedBlueprints) {
        this.notFinishedBlueprints = notFinishedBlueprints;
    }
}
