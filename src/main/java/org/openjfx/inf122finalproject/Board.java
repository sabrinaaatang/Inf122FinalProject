package org.openjfx.inf122finalproject;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import java.util.ArrayList;

public class Board  extends GridPane {
    private int height;
    private int width;
    private BoardPosition[][] grid; // grid[row][col]
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
//        System.out.println("[BOARD UPDATE] Current board state:");
//        for (int row = 0; row < height; row++) {
//            StringBuilder rowStr = new StringBuilder();
//            for (int col = 0; col < width; col++) {
//                Tile tile = grid[row][col].tile;
//                if (tile.getContainingBlock() == null) {
//                    rowStr.append("EMPTY ");
//                } else {
//                    rowStr.append(tile.getContainingBlock().getBlockType().getTypeIdentifier()).append(" ");
//                }
//            }
//            System.out.println(rowStr);
//        }
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

    public Tile getTileAt(int col, int row) {
        if (col < 0 || col >= width || row < 0 || row >= height) {
            System.out.println("Tile request out of bounds: (" + col + ", " + row + ")");
            return null;
        }
        Tile tile = grid[row][col].tile;

        return tile;
    }

}
