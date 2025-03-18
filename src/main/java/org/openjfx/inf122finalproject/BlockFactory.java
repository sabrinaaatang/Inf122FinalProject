package org.openjfx.inf122finalproject;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class BlockFactory {
    private final static Map<BlockType, String> blockStorage = new HashMap<>();
    static {
        blockStorage.put(BlockType.TETRIS_L_SHAPE, "org.openjfx.inf122finalproject.TetrisBlock");
        blockStorage.put(BlockType.CHOCO_CANDY, "org.openjfx.inf122finalproject.CandyBlock");
        blockStorage.put(BlockType.EMPTY_BLOCK, "org.openjfx.inf122finalproject.EmptyBlock");
        blockStorage.put(BlockType.BROWN_CANDY, "org.openjfx.inf122finalproject.CandyBlock");
        blockStorage.put(BlockType.PINK_CANDY, "org.openjfx.inf122finalproject.CandyBlock");
        blockStorage.put(BlockType.YELLOW_CANDY, "org.openjfx.inf122finalproject.CandyBlock");
        //...
    }

    public static Block createBlock(BlockType typeName) {
        String className = blockStorage.get(typeName);

        if (className == null) {
            throw new IllegalArgumentException("Unknown block type: " + typeName);
        }

        try {
            Class<?> _class = Class.forName(className);     //Get class
            Constructor<?>[] constructors = _class.getDeclaredConstructors();   //Get constructor array
            return (Block)constructors[0].newInstance(typeName);    // create new instance.
        } catch (Exception e) {
            throw new RuntimeException("Fail to create block: " + typeName);
        }
    }

    public static void main(String[] args) {
        Block A = BlockFactory.createBlock(BlockType.PINK_CANDY);
        System.out.println(A);
    }

}

