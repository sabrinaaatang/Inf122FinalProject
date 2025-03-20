package org.openjfx.inf122finalproject;

import java.util.Random;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

/**
 * Manages the gameplay mechanics for Tetris.
 * Handles block spawning, movement, rotation, collision detection, and scoring.
 */
public class TetrisManager extends GameManager {
    private final Board board;
    private Block currentPiece;
    private boolean gameOver = false;
    private Timeline dropTimer;
    private Runnable gameOverCallback;
    private final int POINTS_PER_ROW = 10;
    private ScoreManager scoreManager;
    private Player player;

    /**
     * Constructs a TetrisManager for managing Tetris gameplay.
     *
     * @param board The game board where Tetris blocks are placed
     */
    public TetrisManager(Board board) {
        this.board = board;
        this.scoreManager = new ScoreManager();
        this.player = new Player(1, "Player 1");
        spawnNewPiece();
    }

    @Override
    public void setGameOverCallback(Runnable callback) {
        this.gameOverCallback = callback;
    }

    /**
     * Starts the automatic drop timer for the current Tetris piece.
     */
    private void startDropTimer() {
        dropTimer = new Timeline(new KeyFrame(Duration.millis(1000), e -> {
            if (!gameOver) {
                dropPiece();
            }
        }));
        dropTimer.setCycleCount(Timeline.INDEFINITE);
        dropTimer.play();
    }

    /**
     * Pauses the automatic drop timer.
     */
    public void pauseDropTimer() {
        if (dropTimer != null) {
            dropTimer.pause();
        }
    }

    /**
     * Resumes the automatic drop timer.
     */
    public void resumeDropTimer() {
        if (dropTimer != null) {
            dropTimer.play();
        }
    }

    @Override
    public int getScore() {
        return scoreManager.getScore(player);
    }

    @Override
    public boolean isGameOver() {
        return gameOver;
    }

    /**
     * Triggers game over by stopping the drop timer and executing the game-over callback.
     */
    private void triggerGameOver() {
        gameOver = true;
        if (dropTimer != null) {
            dropTimer.stop();
        }
        if (gameOverCallback != null) {
            gameOverCallback.run();  // Notify BoardView
        }
    }

    @Override
    public void updateBoard() {
        board.updateBoard();
    }

    @Override
    public void handleInput(PlayerInput input) {
        this.pauseDropTimer();

        if (currentPiece == null || gameOver) return;
        String actionType = null;

        if (input.getSource() == PlayerInput.InputSource.KEYBOARD) {
            actionType = switch (input.getKeyCode()) {
                case LEFT -> "MOVE_LEFT";
                case RIGHT -> "MOVE_RIGHT";
                case UP -> "ROTATE"; // Rotate block
                case DOWN -> "DROP"; // Faster drop
                default -> null;
            };
        }

        if (input.getSource() == PlayerInput.InputSource.MOUSE) {
            int tileSize = board.getTileSize();
            int startTileX = (int) (input.getStartX() / tileSize);
            int startTileY = (int) (input.getStartY() / tileSize);
            int endTileX = (int) (input.getEndX() / tileSize);
            int endTileY = (int) (input.getEndY() / tileSize);

            int deltaX = endTileX - startTileX;
            int deltaY = endTileY - startTileY;

            if (deltaX == 0 && deltaY == 0) {
                actionType = "ROTATE";
            } else if (Math.abs(deltaX) == 1 && deltaY == 0) {
                actionType = (deltaX == 1) ? "MOVE_RIGHT" : "MOVE_LEFT";
            }
        }

        if (actionType != null) {
            executeAction(actionType);
        }

        this.resumeDropTimer();
    }

    /**
     * Executes the given player action (move, rotate, or drop).
     *
     * @param actionType The type of action to execute
     */
    private void executeAction(String actionType) {
        switch (actionType) {
            case "MOVE_LEFT" -> movePiece(-1);
            case "MOVE_RIGHT" -> movePiece(1);
            case "ROTATE" -> rotatePiece();
            case "DROP" -> dropPiece();
        }
        updateBoard();
    }

    /**
     * Checks and clears full lines from the board, updating the score.
     */
    public void checkLineClear() {
        int width = board.getBoardWidth();
        int height = board.getBoardHeight();
        int linesCleared = 0;

        for (int row = 0; row < height; row++) {
            boolean full = true;
            for (int col = 0; col < width; col++) {
                Tile tile = board.getTileAt(col, row);
                if (tile == null || tile.getContainingBlock() == null) {
                    full = false;
                    break;
                }
            }
            if (full) {
                for (int col = 0; col < width; col++) {
                    Tile tile = board.getTileAt(col, row);
                    if (tile != null) {
                        tile.setContainingBlock(null);
                    }
                }
                linesCleared++;
                shiftRowsDown(row);
            }
        }

        if (linesCleared > 0) {
            scoreManager.updateScore(player, linesCleared * POINTS_PER_ROW);
            updateBoard();
        }
    }

    /**
     * Spawns a new Tetris piece at the top of the board.
     */
    public void spawnNewPiece() {
        BlockType blockType = getBlockType();
        int effectiveWidth = blockType.getEffectiveWidth();
        int boardWidth = board.getBoardWidth();

        Random rand = new Random();
        int spawnX = rand.nextInt(boardWidth - effectiveWidth + 1);
        int spawnY = 0;

        Block newBlock = new Block(blockType, spawnX, spawnY);
        CantPlaceErrorType type = newBlock.placeBlock(board);

        if (type != CantPlaceErrorType.NO_ERR) {
            gameOver = true;
            dropTimer.stop();
            return;
        }

        currentPiece = newBlock;
        updateBoard();
        startDropTimer();
    }

    private BlockType getBlockType() {
        return BlockFactory.createBlock("LShape");
    }

    private void movePiece(int posCount) {
        currentPiece.removeBlock(board);
        currentPiece.getCentreOfMass().x += posCount;

        CantPlaceErrorType type = currentPiece.placeBlock(board);



        if (type == CantPlaceErrorType.OCCUPIED) {
            dropTimer.stop();
            currentPiece.removeBlock(board);
            currentPiece.getCentreOfMass().x -= posCount;
            currentPiece.placeBlock(board);
            checkLineClear();
            spawnNewPiece();
        } else if (type == CantPlaceErrorType.OUT_OF_BOUND) {
            currentPiece.removeBlock(board);
            currentPiece.getCentreOfMass().x -= posCount;
            currentPiece.placeBlock(board);
        }
        else{
            board.updateBoard();
        }
    }

    private void rotatePiece() {
        LShape newBlockType = ((LShape) currentPiece.getBlockType()).getRotated();
        Block newBlock = new Block(newBlockType, currentPiece.getCentreOfMass().x, currentPiece.getCentreOfMass().y);
        currentPiece.removeBlock(board);
        currentPiece = newBlock;

        if (newBlock.placeBlock(board) != CantPlaceErrorType.NO_ERR) {
            newBlock.removeBlock(board);
            currentPiece.placeBlock(board);
        }
        board.updateBoard();
    }

    private void dropPiece() {
        currentPiece.removeBlock(board);
        currentPiece.getCentreOfMass().y += 1;

        if (currentPiece.placeBlock(board) != CantPlaceErrorType.NO_ERR) {
            dropTimer.stop();
            currentPiece.removeBlock(board);
            currentPiece.getCentreOfMass().y -= 1;
            currentPiece.placeBlock(board);
            checkPlacedTop();
            checkLineClear();
            spawnNewPiece();
        } else {
            board.updateBoard();
        }
    }

    private void checkPlacedTop() {
        if (currentPiece.getCentreOfMass().y <= 0) {
            triggerGameOver();
        }
    }

    private void shiftRowsDown(int clearedRow) {
        for (int row = clearedRow - 1; row >= 0; row--) {
            for (int col = 0; col < board.getBoardWidth(); col++) {
                Tile tileAbove = board.getTileAt(col, row);
                Tile tileBelow = board.getTileAt(col, row + 1);
                if (tileBelow != null && tileAbove != null) {
                    tileBelow.setContainingBlock(tileAbove.getContainingBlock());
                    tileAbove.setContainingBlock(null);
                }
            }
        }
    }
}
