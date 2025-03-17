package org.openjfx.inf122finalproject;

public class Candy extends BlockType {
    @Override
    public String getShape() {
        return "Candy";
    }

    @Override
    public int[] getRotationStates() {
        return new int[]{0};
    }
}
