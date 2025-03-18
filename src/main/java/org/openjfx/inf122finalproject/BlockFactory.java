package org.openjfx.inf122finalproject;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class BlockFactory {
    private final static Map<String, String> blockStorage = new HashMap<>();
    static {
        blockStorage.put("L_SHAPE", "org.openjfx.inf122finalproject.TetrisBlock");
        blockStorage.put("RED_CANDY", "org.openjfx.inf122finalproject.CandyBlock");
        blockStorage.put("EMPTY_BLOCK", "org.openjfx.inf122finalproject.EmptyBlock");

        //...
    }

    public static Block createBlock(String typeName) {
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
        Block A = BlockFactory.createBlock("L_SHAPE");
        System.out.println(A);
    }

}

