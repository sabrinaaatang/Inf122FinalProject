package org.openjfx.inf122finalproject;

import javafx.scene.paint.Color;

public class CandyBlock extends Block {
    private final Candy.CandyType type;

    public enum CandyType {
        RED, BLUE, GREEN, YELLOW, PURPLE, ORANGE
    }

    public CandyBlock(Candy.CandyType type) {
        super("Candy");
        this.type = type;
        this.tiles.add(new Tile()); // Candy occupies a single tile
    }

    public Color getColor() {
        return switch (type) {
            case RED -> Color.RED;
            case BLUE -> Color.BLUE;
            case GREEN -> Color.GREEN;
            case YELLOW -> Color.YELLOW;
            case PURPLE -> Color.PURPLE;
            case ORANGE -> Color.ORANGE;
        };
    }

    // New method to return the color name as a String
    public String getColorName() {
        return switch (type) {
            case RED -> "Red";
            case BLUE -> "Blue";
            case GREEN -> "Green";
            case YELLOW -> "Yellow";
            case PURPLE -> "Purple";
            case ORANGE -> "Orange";
        };
    }
}