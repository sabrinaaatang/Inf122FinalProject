package org.openjfx.inf122finalproject;

import javafx.scene.paint.Color;
import java.util.Random;

public class LShape extends BlockType {

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
    }

    private int[][] shapeMatrix;
    private LShapeVariant currentVariant;

    public LShape() {
        super(Color.ORANGE);
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
