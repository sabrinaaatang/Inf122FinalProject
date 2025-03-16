package org.openjfx.inf122finalproject;

import java.util.ArrayList;
import java.awt.Point;

public abstract class Block {
    ArrayList<Tile> tiles; // The tiles this block occupies
    String name;
    Point centreOfMass; // Used for movement/rotation calculations

    public Block(String name) {
        this.name = name;
        this.tiles = new ArrayList<>();
        this.centreOfMass = new Point(0, 0);
    }

    /**
     * Get the block at a specific (x, y) position on the board.
     */
    public static Block getBlock(Board board, int x, int y) {
        if (x < 0 || x >= board.height || y < 0 || y >= board.width) {
            return null; // Out of bounds check
        }

        Tile tile = board.grid[x][y].tile;
        return (tile != null) ? tile.containingBlock : null;
    }

    /**
     * Places the block on the board by setting it in its corresponding tiles.
     */
    public void placeBlock(Board board) {
        for (Tile tile : tiles) {
            BoardPosition position = board.findBoardPosition(tile);
            if (position != null) {
                position.tile.containingBlock = this; // Associate block with the tile
            }
        }
        board.blocks.add(this); // Track block in board's list
    }

    /**
     * Removes the block from the board.
     */
    public void removeBlock(Board board) {
        for (Tile tile : tiles) {
            BoardPosition position = board.findBoardPosition(tile);
            if (position != null) {
                position.tile.containingBlock = null; // Remove block reference from tile
            }
        }
        board.blocks.remove(this);
    }

    @Override
    public String toString() {
        return "A " + this.name + " Block";
    }
}