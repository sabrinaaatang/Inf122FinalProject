package org.openjfx.inf122finalproject;

public class BlockFactory {
    public static BlockType createBlock(String type) {
        if ("Candy".equalsIgnoreCase(type)) {
            return new Candy();
        } else if ("LShape".equalsIgnoreCase(type)) {
            return new LShape();
        }
        return null;
    }
}
