package org.openjfx.inf122finalproject;

import java.util.*;

public class Tile {
    Block containingBlock; // The block currently occupying this tile

    public Tile() {
        this.containingBlock = null; // Tile starts empty
    }

    /**
     * Finds adjacent tiles that contain blocks.
     */
    public List<Tile> getNeighbors(Board board, int x, int y) {
        List<Tile> neighbors = new ArrayList<>();
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}}; // right, left, down, up

        for (int[] dir : directions) {
            int newX = x + dir[0];
            int newY = y + dir[1];

            if (newX >= 0 && newX < board.height && newY >= 0 && newY < board.width) {
                Tile neighborTile = board.grid[newX][newY].tile;
                if (neighborTile.containingBlock != null) {
                    neighbors.add(neighborTile); // Store only occupied tiles
                }
            }
        }
        return neighbors;
    }
}