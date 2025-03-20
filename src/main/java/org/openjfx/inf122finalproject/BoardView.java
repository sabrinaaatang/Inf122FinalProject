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
    private Button returnToMenuButton;

    private int numPlayers = 1; // Default to 1 player
    private int currentPlayer = 1; // Start with Player 1
    final static int WINDOW_WIDTH = 1000;
    final static int WINDOW_HEIGHT = 800;

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
        if (userToPass.containsKey(userText)) {
            if (!userToPass.get(userText).equals(passText)) {
                enterMenu = false;
            }
        } else {
            userToPass.put(userText, passText);
        }

        if (enterMenu) {
            currUser = userText;
            showMenu(primaryStage);
        } else {
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
        ComboBox<String> gameTypeBox = new ComboBox<>();
        gameTypeBox.getItems().addAll("Candy Crush", "Tetris");
        gameTypeBox.setValue("Candy Crush");

        Label userLabel = new Label("User: " + currUser);

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
        this.numPlayers = numPlayers;
        this.currentPlayer = 1;
        showReadyScreen(primaryStage, gameType, currentPlayer);
    }


    /**
     * Launches the game and initializes the board.
     *
     * @param primaryStage The main stage to display the game
     * @param gameType The type of game being played
     */
    private void launchGame(Stage primaryStage, String gameType) {
        int width = 10;
        int height = 10;
        int tileSize = 50;

        Board board = new Board(tileSize);
        board.initialize(width, height);

        scoreLabel = new Label("Score: 0 (Player " + currentPlayer + ")");
        scoreLabel.setFont(new Font("Arial", 30));
        scoreLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: white;");

        gameOverText = new Text("Game Over! Player " + currentPlayer + " lost.");
        gameOverText.setFont(new Font(40));
        gameOverText.setFill(Color.RED);
        gameOverText.setVisible(false);

        returnToMenuButton = new Button("Return to Menu");
        returnToMenuButton.setFont(new Font(20));
        returnToMenuButton.setVisible(false);
        returnToMenuButton.setOnAction(e -> showMenu(primaryStage)); // Go back to the menu

        StackPane boardContainer = new StackPane(board);
        boardContainer.setAlignment(Pos.CENTER);

        VBox root = new VBox(10, scoreLabel, boardContainer, gameOverText, returnToMenuButton);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #333;");

        gameManager = gameType.equals("Candy Crush") ? new CandyCrushManager(board) : new TetrisManager(board);

        Scene gameScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        primaryStage.setResizable(false);

        gameScene.setOnMousePressed(this::handleMousePress);
        gameScene.setOnMouseReleased(this::handleMouseRelease);
        gameScene.setOnKeyPressed(event -> handleKeyPress(event));

        primaryStage.setScene(gameScene);
        primaryStage.setTitle(gameType + " - Player " + currentPlayer);
        primaryStage.show();

        centerWindow(primaryStage);
        startGameLoop(primaryStage, gameType);
    }

    /**
     * Starts the game loop to check for game-over conditions and update the score.
     */
    private void startGameLoop(Stage primaryStage, String gameType) {
        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                updateScore();

                // Check for game-over condition
                if (gameManager.isGameOver()) {
                    gameOverText.setText("Player " + currentPlayer + " Game Over!");
                    gameOverText.setVisible(true);
                    gameLoop.stop();

                    javafx.application.Platform.runLater(() -> returnToMenuButton.setVisible(true));

                    // if it's Player 1 and there's a Player 2, delay transition
                    if (numPlayers == 2 && currentPlayer == 1) {
                        currentPlayer = 2; // Switch to Player 2

                        double windowX = primaryStage.getX();
                        double windowY = primaryStage.getY();
                        double windowWidth = primaryStage.getWidth();
                        double windowHeight = primaryStage.getHeight();

                        new Thread(() -> {
                            try {
                                Thread.sleep(2000); // wait 2 seconds before switching
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            javafx.application.Platform.runLater(() -> {
                                showReadyScreen(primaryStage, gameType, 2);

                                // restore window position and size
                                primaryStage.setX(windowX);
                                primaryStage.setY(windowY);
                                primaryStage.setWidth(windowWidth);
                                primaryStage.setHeight(windowHeight);
                            });
                        }).start();
                    }
                }
            }
        };
        gameLoop.start();
    }



    private void showReadyScreen(Stage primaryStage, String gameType, int playerNumber) {
        Label readyLabel = new Label("Ready Player " + playerNumber);
        readyLabel.setFont(new Font("Arial", 30));
        readyLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: white;");

        Button startButton = new Button("Start");
        startButton.setOnAction(e -> {
            launchGame(primaryStage, gameType);
        });

        VBox readyLayout = new VBox(20, readyLabel, startButton);
        readyLayout.setAlignment(Pos.CENTER);
        readyLayout.setStyle("-fx-background-color: #222; -fx-text-fill: white;");

        Scene readyScene = new Scene(readyLayout, 400, 300);
        primaryStage.setScene(readyScene);
        primaryStage.setTitle("Ready Player " + playerNumber);
        centerWindow(primaryStage);
        primaryStage.show();
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

        if (gameManager != null) {
            PlayerInput input = new PlayerInput(PlayerInput.InputSource.MOUSE, pressX, pressY, releaseX, releaseY, event);
            gameManager.handleInput(input);
        }
    }

    /**
     * Handles keyboard input for game interactions (primarily for Tetris).
     * Pauses the Tetris drop timer when a key is pressed.
     *
     * @param event The key press event
     */
    private void handleKeyPress(javafx.scene.input.KeyEvent event) {
        System.out.println("Key Pressed: " + event.getCode());

        if (gameManager != null) {
            if (gameManager instanceof TetrisManager) {
                ((TetrisManager) gameManager).pauseDropTimer();
            }
            PlayerInput input = new PlayerInput(PlayerInput.InputSource.KEYBOARD, event.getCode());
            gameManager.handleInput(input);
        }
    }

    /**
     * Updates the score display based on the current game score.
     */
    private void updateScore() {
        if (gameManager != null) {
            scoreLabel.setText("Score: " + gameManager.getScore() + " (Player " + currentPlayer + ")");
        }
    }

    /**
     *  Center the window
     * @param stage The stage
     */
    private void centerWindow(Stage stage) {
        stage.setX((javafx.stage.Screen.getPrimary().getVisualBounds().getWidth() - stage.getWidth()) / 2);
        stage.setY((javafx.stage.Screen.getPrimary().getVisualBounds().getHeight() - stage.getHeight()) / 2);
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
