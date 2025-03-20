package org.openjfx.inf122finalproject;

import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;

/**
 * Represents the game board, which consists of a grid of tiles.
 * Extends GridPane to integrate with JavaFX for graphical display.
 */
public class Board extends GridPane {
    private int height;
    private int width;
    private BoardPosition[][] grid; // Grid stored as grid[row][col]
    private int tileSize;

    /**
     * Constructs a Board with a specified tile size.
     *
     * @param tileSize The size of each tile in pixels
     */
    public Board(int tileSize) {
        this.tileSize = tileSize;
    }

    /**
     * Initializes the board with the specified width and height.
     * Creates a grid of BoardPositions, each containing a Tile.
     *
     * @param width  The number of columns in the board
     * @param height The number of rows in the board
     */
    public void initialize(int width, int height) {
        this.width = width;
        this.height = height;
        this.grid = new BoardPosition[height][width];

        this.setHgap(0);
        this.setVgap(0);
        this.setAlignment(Pos.CENTER);

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                grid[row][col] = new BoardPosition(row, col, new Tile(tileSize));
                this.add(grid[row][col].tile, col, row);
            }
        }
    }

    /**
     * Updates the board state. The method is currently commented out but can be used for debugging.
     * It prints the board state with tile contents.
     */
    public void updateBoard() {
        // Uncomment for debugging purposes
        /*
        System.out.println("[BOARD UPDATE] Current board state:");
        for (int row = 0; row < height; row++) {
            StringBuilder rowStr = new StringBuilder();
            for (int col = 0; col < width; col++) {
                Tile tile = grid[row][col].tile;
                if (tile.getContainingBlock() == null) {
                    rowStr.append("EMPTY ");
                } else {
                    rowStr.append(tile.getContainingBlock().getBlockType().getTypeIdentifier()).append(" ");
                }
            }
            System.out.println(rowStr);
        }
        */
    }

    /**
     * Executes a move on the board by applying a TileManipulator action.
     *
     * @param action The TileManipulator action to be executed
     */
    public void applyMove(TileManipulator action) {
        action.execute();
    }

    /**
     * Gets the width of the board.
     *
     * @return The number of columns in the board
     */
    public int getBoardWidth() {
        return width;
    }

    /**
     * Gets the height of the board.
     *
     * @return The number of rows in the board
     */
    public int getBoardHeight() {
        return height;
    }

    /**
     * Gets the size of each tile in pixels.
     *
     * @return The tile size
     */
    public int getTileSize() {
        return tileSize;
    }

    /**
     * Retrieves the tile at a specific position on the board.
     * Prints a warning if the requested position is out of bounds.
     *
     * @param col The column index of the tile
     * @param row The row index of the tile
     * @return The Tile at the specified position, or null if out of bounds
     */
    public Tile getTileAt(int col, int row) {
        if (col < 0 || col >= width || row < 0 || row >= height) {
            System.out.println("Tile request out of bounds: (" + col + ", " + row + ")");
            return null;
        }
        return grid[row][col].tile;
    }
}
