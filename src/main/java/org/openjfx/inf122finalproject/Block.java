package org.openjfx.inf122finalproject;

import java.awt.Point;
import java.util.ArrayList;

public class Block {
    private ArrayList<Tile> containingTiles;
    private BlockType blockType;
    private Point centreOfMass;

    public Block(BlockType blockType, int centerX, int centerY) {
        this.blockType = blockType;
        this.centreOfMass = new Point(centerX, centerY);
        this.containingTiles = new ArrayList<>();
    }

    public void placeBlock(Board board) {
        for (Tile tile : containingTiles) {
            BoardPosition position = board.findBoardPosition(tile);
            if (position != null) {
                position.tile.setContainingBlock(this);
            }
        }
    }

    public void removeBlock(Board board) {
        for (Tile tile : containingTiles) {
            BoardPosition position = board.findBoardPosition(tile);
            if (position != null) {
                position.tile.setContainingBlock(null);
            }
        }
    }

    public void addTile(Tile tile) {
        containingTiles.add(tile);
    }

    public BlockType getBlockType() {
        return blockType;
    }
}
