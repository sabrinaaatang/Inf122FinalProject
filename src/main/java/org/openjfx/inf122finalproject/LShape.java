package org.openjfx.inf122finalproject;

import javafx.scene.paint.Color;
import java.util.Random;

/**
 * Represents an L-shaped block used in the game.
 * The block can take different variants and rotate between them.
 */
public class LShape extends BlockType {

    private static final Color[] COLORS = {
            Color.ORANGE, Color.BLUE, Color.RED, Color.GREEN, Color.PURPLE, Color.CYAN, Color.YELLOW
    };

    /**
     * Enum representing different possible L-shape variants.
     * Each variant is defined by a 2D matrix representation.
     */
    public enum LShapeVariant {
        RIGHT_L(new int[][] { {1, 0}, {1, 1} }),
        INVERTED_RIGHT_L(new int[][] { {1, 1}, {1, 0} }),
        INVERTED_LEFT_L(new int[][] { {1, 1}, {0, 1} }),
        LEFT_L(new int[][] { {0, 1}, {1, 1} }),
        VERTICAL(new int[][] { {1, 0}, {1, 0} }),
        HORIZONTAL(new int[][] { {0, 0}, {1, 1} });

        private final int[][] shape;

        /**
         * Constructs an L-shape variant with a specified shape matrix.
         *
         * @param shape A 2D array representing the block's structure
         */
        LShapeVariant(int[][] shape) {
            this.shape = shape;
        }

        /**
         * Gets the shape matrix of the variant.
         *
         * @return A 2D array representing the variant's shape
         */
        public int[][] getShape() {
            return shape;
        }

        /**
         * Gets the next rotation variant of the L-shape.
         *
         * @param current The current LShape variant
         * @return The next LShape variant in the rotation sequence
         */
        public static LShapeVariant getNextVariant(LShapeVariant current) {
            LShapeVariant[] variants = values();
            int nextIndex = (current.ordinal() + 1) % variants.length;
            return variants[nextIndex];
        }
    }

    private int[][] shapeMatrix;
    private LShapeVariant currentVariant;

    /**
     * Constructs an LShape block with a random variant and color.
     */
    public LShape() {
        super(randomColor());
        LShapeVariant[] variants = LShapeVariant.values();
        Random rand = new Random();
        currentVariant = variants[rand.nextInt(variants.length)];
        shapeMatrix = currentVariant.getShape();
    }

    /**
     * Constructs an LShape block with a specific variant and color.
     *
     * @param variant The variant of the L-shape
     * @param color   The color of the block
     */
    private LShape(LShapeVariant variant, Color color) {
        super(color);
        this.currentVariant = variant;
        this.shapeMatrix = variant.getShape();
    }

    /**
     * Rotates the LShape to its next variant.
     *
     * @return A new LShape instance with the rotated variant
     */
    public LShape getRotated() {
        LShapeVariant newVariant = LShapeVariant.getNextVariant(currentVariant);
        return new LShape(newVariant, super.getColor());
    }

    /**
     * Gets the shape identifier of the block.
     *
     * @return The string "LShape"
     */
    @Override
    public String getShape() {
        return "LShape";
    }

    /**
     * Gets the current rotation state of the block.
     *
     * @return A 2D array representing the block's current shape
     */
    @Override
    public int[][] getRotationStates() {
        return shapeMatrix;
    }

    /**
     * Gets the effective width of the block.
     *
     * @return The width of the block based on its shape matrix
     */
    @Override
    public int getEffectiveWidth() {
        return shapeMatrix[0].length;
    }

    /**
     * Gets the effective height of the block.
     *
     * @return The height of the block based on its shape matrix
     */
    @Override
    public int getEffectiveHeight() {
        return shapeMatrix.length;
    }

    /**
     * Selects a random color from the predefined set of colors.
     *
     * @return A randomly selected Color
     */
    private static Color randomColor() {
        Random rand = new Random();
        return COLORS[rand.nextInt(COLORS.length)];
    }
}
