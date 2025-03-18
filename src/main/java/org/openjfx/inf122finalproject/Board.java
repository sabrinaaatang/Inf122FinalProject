package org.openjfx.inf122finalproject;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import java.util.ArrayList;

public class Board  extends GridPane {
    private int height;
    private int width;
    private BoardPosition[][] grid;
    private int tileSize;
    public Board(int tileSize) {
        this.tileSize = tileSize;
    }

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

    public void updateBoard() {

    }

    public void applyMove(TileManipulator action) {
        action.execute();
    }

    public int getBoardWidth() {
        return width;
    }

    public int getBoardHeight() {
        return height;
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
