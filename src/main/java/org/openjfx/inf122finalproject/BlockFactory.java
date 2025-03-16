package org.openjfx.inf122finalproject;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class BlockFactory {
    private static final Map<String, Class<? extends BlockType>> blockRegistry = new HashMap<>();

    static {
//        blockRegistry.put("L_SHAPE", LShape.class);
        blockRegistry.put("CANDY", Candy.class);
    }

    public static BlockType createBlock(String typeName) {
        Class<? extends BlockType> blockClass = blockRegistry.get(typeName);

        if (blockClass == null) {
            throw new IllegalArgumentException("Unknown block type: " + typeName);
        }

        try {
            if (typeName.equals("CANDY")) {
                Candy.CandyType randomType = Candy.CandyType.values()[new Random().nextInt(Candy.CandyType.values().length)];
                return new Candy(randomType);
            }
            return blockClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Failed to create block: " + typeName, e);
        }
    }
}
