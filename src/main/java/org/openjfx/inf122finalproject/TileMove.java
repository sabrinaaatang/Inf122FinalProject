package org.openjfx.inf122finalproject;

/**
 * Represents a tile movement action where a block is moved from one tile to another.
 * Implements the {@link TileManipulator} interface.
 */
public class TileMove implements TileManipulator {
    private Board board;
    private int x1, y1; // Source tile coordinates
    private int x2, y2; // Target tile coordinates

    /**
     * Constructs a TileMove action for moving a block from one tile to another.
     *
     * @param board The game board on which the movement occurs
     * @param x1    The x-coordinate of the source tile
     * @param y1    The y-coordinate of the source tile
     * @param x2    The x-coordinate of the target tile
     * @param y2    The y-coordinate of the target tile
     */
    public TileMove(Board board, int x1, int y1, int x2, int y2) {
        this.board = board;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    /**
     * Executes the tile movement action.
     * Moves a block from the source tile to the target tile, if the target is empty.
     */
    @Override
    public void execute() {
        Tile sourceTile = board.getTileAt(x1, y1);
        Tile targetTile = board.getTileAt(x2, y2);

        // Ensure tiles exist and the target is empty
        if (sourceTile == null || targetTile == null) return;
        if (targetTile.getContainingBlock() != null) return;

        // Move the block
        Block blockToMove = sourceTile.getContainingBlock();
        targetTile.setContainingBlock(blockToMove);
        sourceTile.setContainingBlock(null);
    }
}
