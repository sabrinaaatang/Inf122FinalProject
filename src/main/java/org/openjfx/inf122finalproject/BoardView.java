package org.openjfx.inf122finalproject;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class BoardView extends Application {
    private double pressX, pressY;
    private Label scoreLabel;
    private Text gameOverText;
    private GameManager gameManager;

    @Override
    public void start(Stage primaryStage) {
        showMenu(primaryStage);
    }

    private void showMenu(Stage primaryStage) {
        // Dropdown for game selection
        ComboBox<String> gameTypeBox = new ComboBox<>();
        gameTypeBox.getItems().addAll("Candy Crush", "Tetris");
        gameTypeBox.setValue("Candy Crush");

        // Buttons for player selection
        Button onePlayerButton = new Button("1 Player");
        Button twoPlayerButton = new Button("2 Players");

        onePlayerButton.setOnAction(e -> startGame(primaryStage, gameTypeBox.getValue(), 1));
        twoPlayerButton.setOnAction(e -> startGame(primaryStage, gameTypeBox.getValue(), 2));

        HBox playerButtons = new HBox(10, onePlayerButton, twoPlayerButton);
        playerButtons.setAlignment(Pos.CENTER);

        VBox menuLayout = new VBox(10,
                new Label("Select Game Mode:"), gameTypeBox,
                new Label("Choose Number of Players:"), playerButtons
        );
        menuLayout.setAlignment(Pos.CENTER);

        Scene menuScene = new Scene(menuLayout, 400, 300);
        primaryStage.setScene(menuScene);
        primaryStage.setTitle("Game Menu");
        primaryStage.show();
    }

    private void startGame(Stage primaryStage, String gameType, int numPlayers) {
        int width = 3;
        int height = 3;
        int tileSize = 50;

        Board board = new Board(tileSize);
        board.initialize(width, height);

        // Initialize score label
        scoreLabel = new Label("Score: 0");
        scoreLabel.setFont(new Font("Arial", 30));
        scoreLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: white;");
        scoreLabel.setAlignment(Pos.CENTER);

        // Initialize game over text
        gameOverText = new Text("Game Over");
        gameOverText.setFont(new Font(40));
        gameOverText.setFill(Color.RED);
        gameOverText.setVisible(false);

        StackPane boardContainer = new StackPane(board);
        boardContainer.setAlignment(Pos.CENTER);

        VBox root = new VBox(10, scoreLabel, boardContainer, gameOverText);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #333;");

        // Choose game manager based on the selected game type
        if (gameType.equals("Candy Crush")) {
            gameManager = new CandyCrushManager(board);
        } else {
            gameManager = new TetrisManager(board);
        }

        // Set up scene
        Scene gameScene = new Scene(root, width * tileSize, height * tileSize);
        gameScene.setOnMousePressed(this::handleMousePress);
        gameScene.setOnMouseReleased(this::handleMouseRelease);
        gameScene.setOnKeyPressed(event -> handleKeyPress(event));

        // Game Over callback
        gameManager.setGameOverCallback(() -> {
            gameOverText.setVisible(true);
            updateScore();  // Show final score when game is over
        });

        primaryStage.setScene(gameScene);
        primaryStage.setTitle(gameType + " - " + numPlayers + " Player(s)");
        primaryStage.show();
    }

    private void handleMousePress(MouseEvent event) {
        if (gameManager instanceof TetrisManager) {
            ((TetrisManager) gameManager).pauseDropTimer();
        }
        pressX = event.getX();
        pressY = event.getY();
    }

    private void handleMouseRelease(MouseEvent event) {
        double releaseX = event.getX();
        double releaseY = event.getY();
        PlayerInput input = new PlayerInput(PlayerInput.InputSource.MOUSE, pressX, pressY, releaseX, releaseY, event);
        gameManager.handleInput(input);
        updateScore();
    }

    private void handleKeyPress(javafx.scene.input.KeyEvent event) {
        System.out.println("Key Pressed: " + event.getCode());
        if (gameManager instanceof TetrisManager) {
            ((TetrisManager) gameManager).pauseDropTimer();
            PlayerInput input = new PlayerInput(PlayerInput.InputSource.KEYBOARD, event.getCode());
            gameManager.handleInput(input);
        }
    }

    private void updateScore() {
        scoreLabel.setText("Score: " + gameManager.getScore());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
