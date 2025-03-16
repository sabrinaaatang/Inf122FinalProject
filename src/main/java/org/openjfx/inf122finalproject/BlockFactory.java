package org.openjfx.inf122finalproject;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class BlockFactory {
    private static final Map<String, Class<? extends Block>> blockRegistry = new HashMap<>();

    static {
        blockRegistry.put("CANDY", CandyBlock.class); // Store actual Block subclass
//        blockRegistry.put("L_SHAPE", LShapeBlock.class);
    }

    public static Block createBlock(String typeName) {
        try {
            if (typeName.equals("CANDY")) {
                Candy.CandyType randomType = Candy.CandyType.values()[new Random().nextInt(Candy.CandyType.values().length)];
                return new CandyBlock(randomType); // Directly return a valid Block subclass
            } else if (typeName.equals("L_SHAPE")) {
                return new LShapeBlock();
            }

            // Use reflection to instantiate a Block subclass
            Class<? extends Block> blockClass = blockRegistry.get(typeName);
            if (blockClass != null) {
                return blockClass.getDeclaredConstructor().newInstance(); // Ensure it creates a Block instance
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to create block: " + typeName, e);
        }
        throw new IllegalArgumentException("Unknown block type: " + typeName);
    }
}
