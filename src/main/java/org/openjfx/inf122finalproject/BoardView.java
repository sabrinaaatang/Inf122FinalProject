package org.openjfx.inf122finalproject;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class BoardView extends Application {
    private double pressX, pressY;
    private Label scoreLabel;

    private GameManager gameManager;
    @Override
    public void start(Stage primaryStage) {

        int width = 10;
        int height = 10;
        int tileSize = 50;

        Board board = new Board(tileSize);
        board.initialize(width, height);

        scoreLabel = new Label("Score: 0");
        scoreLabel.setFont(new Font("Arial", 30));
        scoreLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: white;");
        scoreLabel.setAlignment(Pos.CENTER);

        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(10); // spacing between score and board
        root.setStyle("-fx-background-color: #333;");
        root.getChildren().addAll(scoreLabel, board);

        gameManager = new CandyCrushManager(board);
        //gameManager = new TetrisManager(board);
        Scene scene = new Scene(root, width * tileSize, height * tileSize);

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
            updateScore();
        });

        primaryStage.setTitle("Tile Matching Game Environment");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void updateScore() {
        scoreLabel.setText("Score: " + gameManager.getScore());
    }
    public static void main(String[] args) {
        launch(args);
    }
}
