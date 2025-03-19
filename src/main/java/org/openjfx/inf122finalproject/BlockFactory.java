package org.openjfx.inf122finalproject;

import java.util.Random;

public class BlockFactory {
    private static final String[] BLOCK_TYPES = { "Candy", "LShape" };

    public static BlockType createRandomBlock() {
        Random rand = new Random();
        String type = BLOCK_TYPES[rand.nextInt(BLOCK_TYPES.length)];
        return createBlock(type);
    }

    public static BlockType createBlock(String type) {
        if ("Candy".equalsIgnoreCase(type)) {
            return new Candy();
        } else if ("LShape".equalsIgnoreCase(type)) {
            return new LShape();
        }
        return null;
    }
}
