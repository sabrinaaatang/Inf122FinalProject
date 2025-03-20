package org.openjfx.inf122finalproject;

import java.util.Random;

public class Candy extends BlockType {
    private static final String[] COLORS = { "brown", "choco", "cream", "icecream", "pink", "real", "yellow" };
    private static final Random rand = new Random();

    private final String color;

    public Candy() {
        this(getRandomColor());
    }

    private Candy(String color) {
        super(getImagePath(color), color);
        this.color = color;
    }

    private static String getRandomColor() {
        return COLORS[rand.nextInt(COLORS.length)];
    }

    private static String getImagePath(String color) {
        return "/org/openjfx/inf122finalproject/candy/" + color + "_candy.png";
    }

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
