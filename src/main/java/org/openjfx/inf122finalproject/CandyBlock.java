package org.openjfx.inf122finalproject;

import javafx.scene.paint.Color;
import java.util.Collections;

public class CandyBlock extends Block {
    private final Candy.CandyType type;

    public CandyBlock(Candy.CandyType type) {
        super("Candy");
        this.type = type;
        this.tiles.add(new Tile()); // candy occupies single tile
    }

    public Color getColor() {
        switch (type) {
            case RED:
                return Color.RED;
            case BLUE:
                return Color.BLUE;
            case GREEN:
                return Color.GREEN;
            case YELLOW:
                return Color.YELLOW;
            case PURPLE:
                return Color.PURPLE;
            case ORANGE:
                return Color.ORANGE;
            default:
                return Color.GRAY;
        }
    }
}