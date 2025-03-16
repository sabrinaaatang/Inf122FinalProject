package org.openjfx.inf122finalproject;

import java.util.*;

public class Board {
    int height;
    int width;
    BoardPosition[][] grid;
    ArrayList<Block> blocks;

    public Board(int height, int width) {
        this.height = height;
        this.width = width;
        this.grid = new BoardPosition[height][width];
        this.blocks = new ArrayList<>();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                grid[i][j] = new BoardPosition(i, j, new Tile());
            }
        }
    }

    public void updateBoard() {
        blocks.removeIf(block -> {
            for (Tile tile : block.tiles) {
                BoardPosition position = findBoardPosition(tile);
                if (position != null) {
                    position.tile = new Tile();
                }
            }
            return block.tiles.isEmpty();
        });
    }

    public void applyMove(BlockManipulator action) {
        action.manipulate();
        updateBoard();
    }

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