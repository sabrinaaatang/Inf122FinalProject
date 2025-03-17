package org.openjfx.inf122finalproject;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class BoardView extends Application {
    private double pressX, pressY;
    @Override
    public void start(Stage primaryStage) {

        int width = 10;
        int height = 10;
        int tileSize = 60;

        Board board = new Board(tileSize);
        board.initialize(width, height);

        GameManager gameManager = new CandyCrushManager(board);
        Scene scene = new Scene(board, width * tileSize, height * tileSize);

        scene.setOnMousePressed((MouseEvent event) -> {
            pressX = event.getX();
            pressY = event.getY();
        });

        scene.setOnMouseReleased((MouseEvent event) -> {
            double releaseX = event.getX();
            double releaseY = event.getY();
            PlayerInput input = new PlayerInput(PlayerInput.InputSource.MOUSE, pressX, pressY, releaseX, releaseY);
            gameManager.handleInput(input);
        });


        primaryStage.setTitle("Tile Matching Game Environment");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
