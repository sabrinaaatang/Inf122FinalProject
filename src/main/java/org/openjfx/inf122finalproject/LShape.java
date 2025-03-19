package org.openjfx.inf122finalproject;

import javafx.scene.paint.Color;
import java.util.Random;

public class LShape extends BlockType {

    private static final Color[] COLORS = {
            Color.ORANGE, Color.BLUE, Color.RED, Color.GREEN, Color.PURPLE, Color.CYAN, Color.YELLOW
    };

    public enum LShapeVariant {
        RIGHT_L(new int[][] { {1, 0}, {1, 1} }),
        INVERTED_RIGHT_L(new int[][] { {1, 1}, {1, 0} }),
        INVERTED_LEFT_L(new int[][] { {1, 1}, {0, 1} }),
        LEFT_L(new int[][] { {0, 1}, {1, 1} }),
        VERTICAL(new int[][] { {1, 0}, {1, 0} }),
        HORIZONTAL(new int[][] { {0, 0}, {1, 1} });

        private final int[][] shape;

        LShapeVariant(int[][] shape) {
            this.shape = shape;
        }

        public int[][] getShape() {
            return shape;
        }

        public static LShapeVariant getNextVariant(LShapeVariant current) {
            LShapeVariant[] variants = values();
            int nextIndex = (current.ordinal() + 1) % variants.length;
            return variants[nextIndex];
        }
    }

    private int[][] shapeMatrix;
    private LShapeVariant currentVariant;

    public LShape() {
        super(randomColor());
        LShapeVariant[] variants = LShapeVariant.values();
        Random rand = new Random();
        currentVariant = variants[rand.nextInt(variants.length)];
        shapeMatrix = currentVariant.getShape();
    }

    private LShape(LShapeVariant variant, Color color) {
        super(color);
        this.currentVariant = variant;
        this.shapeMatrix = variant.getShape();
    }

    public LShape getRotated() {
        LShapeVariant newVariant = LShapeVariant.getNextVariant(currentVariant);
        return new LShape(newVariant, super.getColor());
    }

    @Override
    public String getShape() {
        return "LShape";
    }

    @Override
    public int[][] getRotationStates() {
        return shapeMatrix;
    }

    @Override
    public int getEffectiveWidth() {
        return shapeMatrix[0].length;
    }

    @Override
    public int getEffectiveHeight() {
        return shapeMatrix.length;
    }

    private static Color randomColor() {
        Random rand = new Random();
        return COLORS[rand.nextInt(COLORS.length)];
    }
}
