package org.openjfx.inf122finalproject;

import java.util.HashMap;
import java.util.Map;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Represents the main view for the board game.
 * Handles game selection, UI rendering, player input, and game loop execution.
 */
public class BoardView extends Application {
    private double pressX, pressY;
    private Label scoreLabel;
    private Text gameOverText;
    private GameManager gameManager;
    private AnimationTimer gameLoop;

    private Map<String, String> userToPass;
    private String currUser;
    private Label incorrectPass;

    /**
     * JavaFX application entry point. Displays the game selection menu.
     *
     * @param primaryStage The main stage for the application
     */
    @Override
    public void start(Stage primaryStage) {
        prepareLoginScreen(primaryStage);
    }

    /**
     * Sets up member variables for the login process.
     *
     * @param primaryStage The main stage to display the UI
     */
    private void prepareLoginScreen(Stage primaryStage) {
        // Prepares member variables for the login screen
        userToPass = new HashMap<>();
        incorrectPass = new Label();

        showLoginScreen(primaryStage);
    }

    /**
     * Queries the user regarding their username and password.
     *
     * @param primaryStage The main stage to display the login menu
     */
    private void showLoginScreen(Stage primaryStage) {
        Button logInButton = new Button("Log In");

        TextField userField = new TextField();
        userField.setAlignment(Pos.CENTER);
        PasswordField passField = new PasswordField();
        passField.setAlignment(Pos.CENTER);

        logInButton.setOnAction(e -> processLogin(primaryStage, userField.getText(), passField.getText()));

        HBox button = new HBox(10, logInButton);
        button.setAlignment(Pos.CENTER);

        // Asks the user to enter their username and password
        VBox loginLayout = new VBox(10,
            new Label("Enter existing or new username:"), userField,
            new Label("Enter password:"), passField,
            button, incorrectPass);
        loginLayout.setAlignment(Pos.CENTER);

        Scene loginScene = new Scene(loginLayout, 400, 400);
        primaryStage.setScene(loginScene);
        primaryStage.setTitle("Log In");
        primaryStage.show();
    }

    /**
     * Process login authentication.
     *
     * @param primaryStage The main stage to display the UI
     * @param userText The inputted username
     * @param passText The inputted password
     */
    private void processLogin(Stage primaryStage, String userText, String passText) {
        boolean enterMenu = true;
        
        // If the username already exists on record
        if (userToPass.containsKey(userText)) {
            String actualPass = userToPass.get(userText);

            // If the inputted password doesn't match the password on record,
            // it returns back to the login screen
            if (!(actualPass.equals(passText))) {
                enterMenu = false;
            }
        }
        // If the username doesn't exist, add a new username / password pair to the record
        else {
            userToPass.put(userText, passText);
        }

        if (enterMenu) {
            // Goes to the menu screen
            currUser = userText;
            showMenu(primaryStage);
        }
        else {
            // When going back to the login screen,
            // a text shows an incorrect password message
            incorrectPass.setText("Incorrect password");
            showLoginScreen(primaryStage);
        }
    }

    /**
     * Displays the game selection menu where the user can choose a game mode and number of players.
     *
     * @param primaryStage The main stage to display the menu
     */
    private void showMenu(Stage primaryStage) {
        // Dropdown for game selection
        ComboBox<String> gameTypeBox = new ComboBox<>();
        gameTypeBox.getItems().addAll("Candy Crush", "Tetris");
        gameTypeBox.setValue("Candy Crush");

        // Show the current username
        Label userLabel = new Label("User: " + currUser);

        // Buttons for player selection
        Button onePlayerButton = new Button("1 Player");
        Button twoPlayerButton = new Button("2 Players");

        onePlayerButton.setOnAction(e -> startGame(primaryStage, gameTypeBox.getValue(), 1));
        twoPlayerButton.setOnAction(e -> startGame(primaryStage, gameTypeBox.getValue(), 2));

        HBox playerButtons = new HBox(10, onePlayerButton, twoPlayerButton);
        playerButtons.setAlignment(Pos.CENTER);

        VBox menuLayout = new VBox(10,
                userLabel,
                new Label("Select Game Mode:"), gameTypeBox,
                new Label("Choose Number of Players:"), playerButtons
        );
        menuLayout.setAlignment(Pos.CENTER);

        Scene menuScene = new Scene(menuLayout, 400, 300);
        primaryStage.setScene(menuScene);
        primaryStage.setTitle("Game Menu");
        primaryStage.show();
    }

    /**
     * Initializes and starts the selected game with the specified number of players.
     *
     * @param primaryStage The main stage to display the game
     * @param gameType     The selected game type (e.g., "Candy Crush", "Tetris")
     * @param numPlayers   The number of players
     */
    private void startGame(Stage primaryStage, String gameType, int numPlayers) {
        int width = 10;
        int height = 10;
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

        // Choose the appropriate game manager
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

        primaryStage.setScene(gameScene);
        primaryStage.setTitle(gameType + " - " + numPlayers + " Player(s)");
        primaryStage.show();

        // Start game loop to monitor game-over state
        startGameLoop();
    }

    /**
     * Starts the game loop to check for game-over conditions.
     */
    private void startGameLoop() {
        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (gameManager.isGameOver()) {
                    gameOverText.setVisible(true);
                    gameLoop.stop(); // Stop checking once the game is over
                }
            }
        };
        gameLoop.start();
    }

    /**
     * Handles mouse press events for tile interaction.
     * Pauses Tetris drop timer if applicable.
     *
     * @param event The mouse press event
     */
    private void handleMousePress(MouseEvent event) {
        if (gameManager instanceof TetrisManager) {
            ((TetrisManager) gameManager).pauseDropTimer();
        }
        pressX = event.getX();
        pressY = event.getY();
    }

    /**
     * Handles mouse release events to process tile swaps or moves.
     * Updates the game score after the move.
     *
     * @param event The mouse release event
     */
    private void handleMouseRelease(MouseEvent event) {
        double releaseX = event.getX();
        double releaseY = event.getY();
        PlayerInput input = new PlayerInput(PlayerInput.InputSource.MOUSE, pressX, pressY, releaseX, releaseY, event);
        gameManager.handleInput(input);
        updateScore();
    }

    /**
     * Handles keyboard input for game interactions (primarily for Tetris).
     * Pauses the Tetris drop timer when a key is pressed.
     *
     * @param event The key press event
     */
    private void handleKeyPress(javafx.scene.input.KeyEvent event) {
        System.out.println("Key Pressed: " + event.getCode());
        if (gameManager instanceof TetrisManager) {
            ((TetrisManager) gameManager).pauseDropTimer();
            PlayerInput input = new PlayerInput(PlayerInput.InputSource.KEYBOARD, event.getCode());
            gameManager.handleInput(input);
        }
    }

    /**
     * Updates the score display based on the current game score.
     */
    private void updateScore() {
        scoreLabel.setText("Score: " + gameManager.getScore());
    }

    /**
     * Main method to launch the JavaFX application.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
