package org.openjfx.inf122finalproject;

import java.util.Random;

public class Candy extends BlockType {
    private static final String[] COLORS = { "brown", "choco", "cream", "icecream", "pink", "real", "yellow" };

    public Candy() {
        super(getRandomImagePath(), getRandomColor());
    }

    private static String getRandomColor() {
        Random rand = new Random();
        return COLORS[rand.nextInt(COLORS.length)];
    }

    private static String getRandomImagePath() {
        String color = getRandomColor();
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
