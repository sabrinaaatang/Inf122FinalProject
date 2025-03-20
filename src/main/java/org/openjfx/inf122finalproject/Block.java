package org.openjfx.inf122finalproject;

import java.awt.Point;
import java.util.ArrayList;

/**
 * Represents a block in the game, which consists of multiple tiles and a defined shape.
 */
public class Block {
    private ArrayList<Tile> containingTiles;
    private BlockType blockType;
    private Point centreOfMass;

    /**
     * Constructs a Block with the given type and center position.
     *
     * @param blockType The type of block, defining its shape and behavior
     * @param centerX   The x-coordinate of the block's center
     * @param centerY   The y-coordinate of the block's center
     */
    public Block(BlockType blockType, int centerX, int centerY) {
        this.blockType = blockType;
        this.centreOfMass = new Point(centerX, centerY);
        this.containingTiles = new ArrayList<>();
    }

    /**
     * Attempts to place the block on the board.
     * The block is placed based on its defined shape.
     *
     * @param board The game board on which the block is placed
     * @return OUT_OF_BOUND if the block was out of bound, OCCUPIED if it overlaps with existing blocks, NO_ERR -> success
     */
    public CantPlaceErrorType placeBlock(Board board) {
        if (isOutOfBounds(board)) {
            System.out.println("Block out of bounds");
            return CantPlaceErrorType.OUT_OF_BOUND;
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
                    if (tile != null && tile.getContainingBlock() == null) {
                        containingTiles.add(tile);
                        tile.setContainingBlock(this);
                    } else {
                        // Block placement fails due to existing block presence
                        System.out.println("Existing Block");
                        return CantPlaceErrorType.OCCUPIED;
                    }
                }
            }
        }
        return CantPlaceErrorType.NO_ERR;       // badass
    }

    /**
     * Removes the block from the board, clearing its tiles.
     *
     * @param board The game board from which the block is removed
     */
    public void removeBlock(Board board) {
        for (Tile tile : containingTiles) {
            tile.setContainingBlock(null);
        }
        containingTiles.clear();
    }

    /**
     * Checks if the block is out of bounds of the board.
     *
     * @param board The game board to check against
     * @return true if the block is out of bounds, false otherwise
     */
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

    /**
     * Returns the block type.
     *
     * @return The BlockType of this block
     */
    public BlockType getBlockType() {
        return blockType;
    }

    /**
     * Returns the center of mass of the block.
     *
     * @return A Point representing the block's center position
     */
    public Point getCentreOfMass() {
        return centreOfMass;
    }
}
