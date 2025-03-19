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
        int tileSize = 50;

        Board board = new Board(tileSize);
        board.initialize(width, height);

        GameManager gameManager;
//        gameManager = new CandyCrushManager(board);
        gameManager = new TetrisManager(board);
        Scene scene = new Scene(board, width * tileSize, height * tileSize);

        scene.setOnMousePressed((MouseEvent event) -> {
            if (gameManager instanceof TetrisManager) {
                ((TetrisManager) gameManager).pauseDropTimer();
            }
            pressX = event.getX();
            pressY = event.getY();
        });


        scene.setOnMouseReleased((MouseEvent event) -> {
            double releaseX = event.getX();
            double releaseY = event.getY();
            PlayerInput input = new PlayerInput(PlayerInput.InputSource.MOUSE, pressX, pressY, releaseX, releaseY, event);
            gameManager.handleInput(input);
        });

        scene.setOnKeyPressed(event -> {
            System.out.println("Key Pressed: " + event.getCode());  // Add this line to verify
            if (gameManager instanceof TetrisManager) {
                ((TetrisManager) gameManager).pauseDropTimer();
                PlayerInput input = new PlayerInput(PlayerInput.InputSource.KEYBOARD, event.getCode());
                gameManager.handleInput(input);  // Pass the keyboard input to TetrisManager
            }
        });


        primaryStage.setTitle("Tile Matching Game Environment");
        primaryStage.setScene(scene);
        primaryStage.show();
        board.requestFocus();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
