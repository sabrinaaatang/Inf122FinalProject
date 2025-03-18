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

    public boolean placeBlock(Board board) {
        if (isOutOfBounds(board)) {
            return false;
        }
        int[][] shape = blockType.getRotationStates();
        int shapeRows = shape.length;
        int shapeCols = shape[0].length;


        int originX = centreOfMass.x;
        int originY = centreOfMass.y;

        for (int r = 0; r < shapeRows; r++) {
            for (int c = 0; c < shapeCols; c++) {
                if (shape[r][c] == 1) {
                    int boardX = originX + c;
                    int boardY = originY + r;
                    Tile tile = board.getTileAt(boardX, boardY);
                    if (tile != null && tile.containingBlock == null) {
                        containingTiles.add(tile);
                        tile.setContainingBlock(this);
                    }
                    else {
                        //We cannot place the block as there is an existing block in the tile(s)
                        return false;
                    }
                }
            }
        }
        return true;
    }
    public void removeBlock(Board board) {
        for (Tile tile : containingTiles) {
            tile.setContainingBlock(null);
        }
        containingTiles.clear();
    }
   private boolean isOutOfBounds(Board board) {
        BlockType type = this.getBlockType();
        int effectiveWidth = type.getEffectiveWidth();
        int effectiveHeight = type.getEffectiveHeight();

        int originRow = this.getCentreOfMass().x;
        int originCol = this.getCentreOfMass().y;

        return (originRow < 0 || originCol < 0 ||
                originRow + effectiveHeight > board.getBoardHeight() ||
                originCol + effectiveWidth > board.getBoardWidth());
    }

    public BlockType getBlockType() {
        return blockType;
    }

    public Point getCentreOfMass() {
        return centreOfMass;
    }
}
