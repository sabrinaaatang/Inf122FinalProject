package org.openjfx.inf122finalproject;

import javafx.scene.paint.Color;
import java.util.Random;

public abstract class BlockType {
    private Color color;

    public BlockType() {
        this.color = generatePresetColor();
    }

    private Color generatePresetColor() {
        Color[] presetColors = new Color[] {
                Color.RED,
                Color.BLUE,
                Color.GREEN,
                Color.YELLOW,
                Color.ORANGE,
                Color.PURPLE,
                Color.CYAN,
                Color.MAGENTA
        };
        Random rand = new Random();
        return presetColors[rand.nextInt(presetColors.length)];
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    public abstract String getShape();
    public abstract int[][] getRotationStates();

    public abstract int getEffectiveWidth();

    public abstract int getEffectiveHeight();
}
