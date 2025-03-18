package org.openjfx.inf122finalproject;

import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.awt.Point;

public abstract class Block {
    ArrayList<Tile> tiles;
    boolean isEmpty = true;
    String name;
    int score;
    Tile centreOfMass;
    BlockManipulatorContext bm = new BlockManipulatorContext();
    private ImageView img;
    AutoDropBehavior autoDropBehavior;

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
    public ArrayList<Tile> getTiles() {
        return tiles;
    }

    public void setIsEmpty(boolean empty) {
        isEmpty = empty;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    @Override
    public String toString() {
        return "A " + this.name + " Block";
    }
}
