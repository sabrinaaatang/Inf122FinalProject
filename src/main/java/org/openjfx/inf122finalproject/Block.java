package org.openjfx.inf122finalproject;

import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.awt.Point;

public abstract class Block {
    ArrayList<Tile> tiles;
    String name;
    Tile centreOfMass;
    BlockManipulatorContext bm = new BlockManipulatorContext();
    private ImageView img;

    public Block(String name) {
        this.name = name;
        tiles = new ArrayList<>();
        centreOfMass = null;
    }

    public void placeBlock(Board board) {
        //
    }

    public void removeBlock(Board board) {
        //
    }

    // more functions

    @Override
    public String toString() {
        return "A " + this.name + " Block";
    }
}
