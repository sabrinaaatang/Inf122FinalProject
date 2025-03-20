package org.openjfx.inf122finalproject;

import java.util.Random;

/**
 * Represents a Candy block type used in Candy Crush.
 * Each candy is randomly assigned a color and has a corresponding image.
 */
public class Candy extends BlockType {
    private static final String[] COLORS = { "brown", "choco", "cream", "icecream", "pink", "real", "yellow" };
    private static final Random rand = new Random();

    private final String color;

    /**
     * Constructs a Candy block with a random color.
     */
    public Candy() {
        this(getRandomColor());
    }

    /**
     * Constructs a Candy block with a specified color.
     *
     * @param color The color of the candy
     */
    private Candy(String color) {
        super(getImagePath(color), color);
        this.color = color;
    }

    /**
     * Generates a random color from the predefined list of colors.
     *
     * @return A randomly selected color name as a string
     */
    private static String getRandomColor() {
        return COLORS[rand.nextInt(COLORS.length)];
    }

    /**
     * Generates the image path for a given candy color.
     *
     * @param color The color of the candy
     * @return The file path to the candy image
     */
    private static String getImagePath(String color) {
        return "/org/openjfx/inf122finalproject/candy/" + color + "_candy.png";
    }

    /**
     * Returns the shape of the candy block.
     *
     * @return The string "Candy"
     */
    @Override
    public String getShape() {
        return "Candy";
    }

    /**
     * Returns the rotation states of the candy block.
     * Since candies do not rotate, the state is a single 1x1 matrix.
     *
     * @return A 2D array representing the rotation states
     */
    @Override
    public int[][] getRotationStates() {
        return new int[][] { {1} };
    }

    /**
     * Returns the effective width of the candy block.
     *
     * @return The width of the candy block (always 1)
     */
    @Override
    public int getEffectiveWidth() {
        return 1;
    }

    /**
     * Returns the effective height of the candy block.
     *
     * @return The height of the candy block (always 1)
     */
    @Override
    public int getEffectiveHeight() {
        return 1;
    }
}
