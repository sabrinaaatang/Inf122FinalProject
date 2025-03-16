package org.openjfx.inf122finalproject;

public class Candy extends BlockType {
    public enum CandyType {
        RED, BLUE, GREEN, YELLOW, PURPLE, ORANGE
    }

    private final CandyType candyType;

    public Candy(CandyType type) {
        super("CANDY");
        this.candyType = type;
    }

    public CandyType getCandyType() {
        return candyType;
    }

    @Override
    public int[][] getShape() {
        return new int[][]{{1}};
    }

    @Override
    public int[][] getRotationStates() {
        return new int[][]{{1}}; // no rotation needed
    }

    @Override
    public String toString() {
        return "Candy Block of type: " + candyType;
    }
}