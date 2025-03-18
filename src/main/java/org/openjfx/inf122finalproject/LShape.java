package org.openjfx.inf122finalproject;

import javafx.scene.paint.Color;

import java.util.Random;

public class LShape extends BlockType {


    public enum LShapeVariant {
        RIGHT_L(new int[][] { {1, 0}, {1, 1} }),//INVERTED_RIGHT_L
        LEFT_L(new int[][] { {0, 1}, {1, 1} }),//RIGHT_L
        INVERTED_RIGHT_L(new int[][] { {1, 1}, {1, 0} }),//INVERTED_LEFT_L
        INVERTED_LEFT_L(new int[][] { {1, 1}, {0, 1} }),//LEFT_L
        VERTICAL(new int[][] { {1, 0}, {1, 0} }),//HORIZONTAL
        HORIZONTAL(new int[][] { {0, 0}, {1, 1} });//VERTICAL

        private final int[][] shape;

        LShapeVariant(int[][] shape) {
            this.shape = shape;
        }

        public int[][] getShape() {
            return shape;
        }
    }

    private int[][] shapeMatrix;
    private LShapeVariant currentVariant;
    public LShape() {
        LShapeVariant[] variants = LShapeVariant.values();
        Random rand = new Random();
        currentVariant = variants[rand.nextInt(variants.length)];
        shapeMatrix = currentVariant.getShape();
    }

    public LShapeVariant getCurrentVariant() {
        return currentVariant;
    }

    private LShape(LShapeVariant variant, Color color) {
        currentVariant = variant;
        shapeMatrix = variant.getShape();
        super.setColor(color);
        setColor(Color.ORANGE);
    }

    // Returns a new LShape with the rotated variant, based on a simple switch.
    public LShape getRotated() {
        LShapeVariant newVariant;
        switch (currentVariant) {
            case RIGHT_L:
                newVariant = LShapeVariant.INVERTED_RIGHT_L;
                break;
            case INVERTED_RIGHT_L:
                newVariant = LShapeVariant.INVERTED_LEFT_L;
                break;
            case INVERTED_LEFT_L:
                newVariant = LShapeVariant.LEFT_L;
                break;
            case LEFT_L:
                newVariant = LShapeVariant.RIGHT_L;
                break;
            case VERTICAL:
                newVariant = LShapeVariant.HORIZONTAL;
                break;
            case HORIZONTAL:
                newVariant = LShapeVariant.VERTICAL;
                break;
            default:
                newVariant = currentVariant;
                break;
        }
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
}
