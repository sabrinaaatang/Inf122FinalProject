package org.openjfx.inf122finalproject;
import javafx.scene.layout.GridPane;
import java.util.ArrayList;

public class Board  extends GridPane {
    private int height;
    private int width;
    private BoardPosition[][] grid;
    private ArrayList<Block> blocks;
    private int tileSize;

    public Board(int tileSize) {
        this.tileSize = tileSize;
        this.blocks = new ArrayList<>();
    }

    public void initialize(int width, int height) {
        this.width = width;
        this.height = height;
        this.grid = new BoardPosition[height][width];
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                grid[row][col] = new BoardPosition(row, col, new Tile(tileSize));
                this.add(grid[row][col].tile, col, row);
            }
        }
    }

    public void updateBoard() {
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                grid[row][col].tile.updateColor();
            }
        }
    }

    public void applyMove(TileManipulator action) {
        action.execute();
        updateBoard();
    }

    public int getBoardWidth() {
        return width;
    }

    public int getBoardHeight() {
        return height;
    }

    public BoardPosition findBoardPosition(Tile tile) {
        for (BoardPosition[] boardPositions : grid) {
            for (BoardPosition boardPosition : boardPositions) {
                if (boardPosition.tile == tile) {
                    return boardPosition;
                }
            }
        }
        return null;
    }

    public int getTileSize() {
        return tileSize;
    }

    public Tile getTileAt(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            return null;
        }
        return grid[y][x].tile;
    }
}
