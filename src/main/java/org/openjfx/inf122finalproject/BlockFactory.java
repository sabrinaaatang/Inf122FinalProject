package org.openjfx.inf122finalproject;

import java.util.Random;

/**
 * A factory class for creating different types of blocks.
 */
public class BlockFactory {
    private static final String[] BLOCK_TYPES = { "Candy", "LShape" };

    /**
     * Creates a random block type from the available options.
     *
     * @return A randomly selected BlockType
     */
    public static BlockType createRandomBlock() {
        Random rand = new Random();
        String type = BLOCK_TYPES[rand.nextInt(BLOCK_TYPES.length)];
        return createBlock(type);
    }

    /**
     * Creates a specific block type based on the given name.
     *
     * @param type The name of the block type
     * @return A new instance of the corresponding BlockType, or null if the type is not recognized
     */
    public static BlockType createBlock(String type) {
        if ("Candy".equalsIgnoreCase(type)) {
            return new Candy();
        } else if ("LShape".equalsIgnoreCase(type)) {
            return new LShape();
        }
        return null;
    }
}
