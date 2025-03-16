package org.openjfx.inf122finalproject;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.util.Random;
import javafx.scene.shape.StrokeType;

public class BoardView extends Application {
    private static final int TILE_SIZE = 60;
    private static final int BOARD_WIDTH = 10;
    private static final int BOARD_HEIGHT = 10;

    private StackPane selectedTile = null;
    private Rectangle selectedRect = null;

    private Board board;
    private GridPane grid;

    @Override
    public void start(Stage primaryStage) {
        board = new Board(BOARD_HEIGHT, BOARD_WIDTH);
        grid = new GridPane();

        initializeBoard();

        Scene scene = new Scene(grid, BOARD_WIDTH * TILE_SIZE, BOARD_HEIGHT * TILE_SIZE);
        primaryStage.setTitle("Candy Crush Board");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void initializeBoard() {
        Random rand = new Random();
        grid.setHgap(3);
        grid.setVgap(3);

        for (int row = 0; row < BOARD_HEIGHT; row++) {
            for (int col = 0; col < BOARD_WIDTH; col++) {
                // Generate a CandyBlock
                Candy.CandyType randomCandyType = Candy.CandyType.values()[rand.nextInt(Candy.CandyType.values().length)];
                CandyBlock block = new CandyBlock(randomCandyType);

                // Create a Tile and associate it with the block
                Tile tile = new Tile();
                tile.containingBlock = block;
                block.tiles.add(tile); // Store the tile inside the block

                // Store BoardPosition with the Tile inside the board
                board.grid[row][col] = new BoardPosition(row, col, tile);
                board.blocks.add(block); // Track block in the board

                // Create UI representation
                StackPane tilePane = new StackPane();
                Rectangle rect = new Rectangle(TILE_SIZE - 1, TILE_SIZE - 1, block.getColor());

                tilePane.getChildren().add(rect);
                tilePane.setOnMouseClicked(event -> highlightTile(tilePane, rect));

                grid.add(tilePane, col, row);
            }
        }
    }

    private void highlightTile(StackPane tilePane, Rectangle rect) {
        if (selectedTile != null) {
            selectedRect.setStroke(null);
        }

        if (selectedTile == tilePane) {
            selectedTile = null;
            selectedRect = null;
        } else {
            rect.setStroke(Color.BLACK);
            rect.setStrokeWidth(5);
            rect.setStrokeType(StrokeType.INSIDE);
            selectedTile = tilePane;
            selectedRect = rect;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}