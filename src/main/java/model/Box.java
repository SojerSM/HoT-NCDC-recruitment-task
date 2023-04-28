package model;

import java.util.Map;

public class Box {
    private Map<Integer, String> blocks;

    public Box(Map<Integer, String> blocks) {
        this.blocks = blocks;
    }

    public Map<Integer, String> getBlocks() {
        return blocks;
    }

    public void printBlocks() {
        for (Map.Entry<Integer, String> entry : this.blocks.entrySet()) {
            Integer key = entry.getKey();
            String values = entry.getValue();

            System.out.println(key + ": " + values);
        }
    }

}
