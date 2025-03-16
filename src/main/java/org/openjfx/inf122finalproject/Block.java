package org.openjfx.inf122finalproject;

import java.util.ArrayList;
import java.awt.Point;

public abstract class Block {
    ArrayList<Tile> tiles;
    String name;
    Point centreOfMass;

    public Block(String name) {
        this.name = name;
        this.tiles = new ArrayList<>();
        this.centreOfMass = new Point(0, 0);
    }

    public void placeBlock(Board board) {
        for (Tile tile : tiles) {
            BoardPosition position = board.findBoardPosition(tile);
            if (position != null) {
                position.tile = tile; // Place block on the board
            }
        }
        board.blocks.add(this);
    }

    public void removeBlock(Board board) {
        tiles.forEach(tile -> {
            BoardPosition position = board.findBoardPosition(tile);
            if (position != null) {
                position.tile = new Tile(); // Clear tile when block is removed
            }
        });
        board.blocks.remove(this);
    }

    @Override
    public String toString() {
        return "A " + this.name + " Block";
    }
}