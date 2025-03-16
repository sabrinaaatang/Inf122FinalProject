package org.openjfx.inf122finalproject;

import java.util.*;

public class Board {
    int height;
    int width;
    BoardPosition[][] grid; // 2D array representing board positions
    ArrayList<Block> blocks; // List of all blocks on the board

    public Board(int height, int width) {
        this.height = height;
        this.width = width;
        this.grid = new BoardPosition[height][width];
        this.blocks = new ArrayList<>();

        // Initialize board with empty tiles
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                grid[i][j] = new BoardPosition(i, j, new Tile()); // Store BoardPosition with Tile
            }
        }
    }

    /**
     * Updates the board after a match is cleared.
     */
    public void updateBoard() {
        blocks.removeIf(block -> {
            for (Tile tile : block.tiles) {
                BoardPosition position = findBoardPosition(tile);
                if (position != null) {
                    position.tile.containingBlock = null; // Clear the block reference
                }
            }
            return block.tiles.isEmpty(); // Remove blocks that have no tiles left
        });
    }

    /**
     * Finds the BoardPosition of a given tile.
     */
    public BoardPosition findBoardPosition(Tile tile) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (grid[i][j].tile == tile) {
                    return grid[i][j];
                }
            }
        }
        return null;
    }
}