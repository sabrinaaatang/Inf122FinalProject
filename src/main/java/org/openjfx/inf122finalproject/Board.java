package org.openjfx.inf122finalproject;

import java.util.*;

/* Multiple of tiles */
public class Board {
    int height;
    int width;
    Tile[][] grid;


    public Board(int height, int width) {
        this.height = height;
        this.width = width;
        grid = new Tile[height][width];
        initTiles();
    }

    public Tile[][] getGrid() { return grid; }


    private void initTiles() {
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                grid[row][col] = new Tile(col, row);
            }
        }
    }

    
}