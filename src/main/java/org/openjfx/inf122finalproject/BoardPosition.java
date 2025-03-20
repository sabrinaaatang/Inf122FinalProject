package org.openjfx.inf122finalproject;

/**
 * Represents a position on the board, containing coordinates and a tile.
 */
public class BoardPosition {
    public int x;
    public int y;
    public Tile tile;

    /**
     * Constructs a BoardPosition with specified coordinates and an associated tile.
     *
     * @param x    The x-coordinate (column) of the position
     * @param y    The y-coordinate (row) of the position
     * @param tile The tile located at this position
     */
    public BoardPosition(int x, int y, Tile tile) {
        this.x = x;
        this.y = y;
        this.tile = tile;
    }
}
