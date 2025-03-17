package org.openjfx.inf122finalproject;

public class BoardPosition {
    public int x;
    public int y;
    public Tile tile;

    public BoardPosition(int x, int y, Tile tile) {
        this.x = x;
        this.y = y;
        this.tile = tile;
    }
}
