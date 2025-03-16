package org.openjfx.inf122finalproject;

public class BoardPosition {
    int x;
    int y;
    Tile tile;

    public BoardPosition(int x, int y, Tile tile) {
        this.x = x;
        this.y = y;
        this.tile = tile;
    }
}