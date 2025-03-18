package org.openjfx.inf122finalproject;

public class Candy extends BlockType {
    @Override
    public String getShape() {
        return "Candy";
    }

    @Override
    public int[][] getRotationStates() {
        return new int[][] { {1} };
    }

    @Override
    public int getEffectiveWidth() {
        return 1;
    }

    @Override
    public int getEffectiveHeight() {
        return 1;
    }
}
