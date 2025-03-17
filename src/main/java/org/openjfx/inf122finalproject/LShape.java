package org.openjfx.inf122finalproject;

public class LShape extends BlockType {
    @Override
    public String getShape() {
        return "LShape";
    }

    @Override
    public int[] getRotationStates() {
        return new int[]{0, 90, 180, 270};
    }
}
