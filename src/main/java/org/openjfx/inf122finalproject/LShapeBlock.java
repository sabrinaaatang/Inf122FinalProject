package org.openjfx.inf122finalproject;

import javafx.scene.paint.Color;
import java.util.ArrayList;

public class LShapeBlock extends Block {
    public LShapeBlock() {
        super("L_SHAPE");

        this.tiles = new ArrayList<>();
        this.tiles.add(new Tile());
        this.tiles.add(new Tile());
        this.tiles.add(new Tile());
        this.tiles.add(new Tile());
    }

    public Color getColor() {
        return Color.DARKBLUE;
    }
}
