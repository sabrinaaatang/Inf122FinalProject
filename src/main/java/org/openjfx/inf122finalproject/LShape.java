package org.openjfx.inf122finalproject;

public class LShape extends BlockType {
    public LShape() {
        super("L_SHAPE");
    }

    @Override
    public int[][] getShape() {
        return new int[][]{
                {1, 0},
                {1, 0},
                {1, 1}
        };
    }

    @Override
    public int[][] getRotationStates() {
        return new int[][]{
                {0, 0, 1},
                {1, 1, 1}
        };
    }
}