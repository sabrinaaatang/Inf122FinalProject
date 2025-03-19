package org.openjfx.inf122finalproject;

import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class TetrisManager extends GameManager {
    private final Board board;
    private Block currentPiece;
    private boolean gameOver = false;
    private Timeline dropTimer;
    public TetrisManager(Board board) {
        this.board = board;
        spawnNewPiece();

    }

    private void startDropTimer() {
        dropTimer = new Timeline(new KeyFrame(Duration.millis(1000), e -> {
            if (!gameOver) {
                dropPiece();
            }
        }));
        dropTimer.setCycleCount(Timeline.INDEFINITE);
        dropTimer.play();
    }

    public void pauseDropTimer() {
        if (dropTimer != null) {
            dropTimer.pause();
        }
    }

    public void resumeDropTimer() {
        if (dropTimer != null) {
            dropTimer.play();
        }
    }

    @Override
    public boolean isGameOver() {
        return gameOver;
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
                case UP -> // Add rotation with up arrow
                        "ROTATE";
                case DOWN -> // Faster drop with down arrow
                        "DROP";
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
                // A horizontal drag of exactly one tile: interpret as a move.
                if (deltaX == 1) {
                    actionType = "MOVE_RIGHT";
                } else if (deltaX == -1) {
                    actionType = "MOVE_LEFT";
                }
            }
        }
        if(actionType != null){
            executeAction(actionType);
        }

        this.resumeDropTimer();
    }

    private void executeAction(String actionType){
        switch (actionType) {
            case "MOVE_LEFT":
                movePiece(-1);
                break;
            case "MOVE_RIGHT":
                movePiece(1);
                break;

            case "ROTATE":
                rotatePiece();
                break;

            case "DROP":
                dropPiece();
                break;
            default:
                break;
        }
        updateBoard();
    }

    public void checkLineClear() {
        int width = board.getBoardWidth();
        int height = board.getBoardHeight();
        boolean lineCleared = false;

        // Check each row to see if it's fully occupied
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
                // Clear the entire row
                for (int col = 0; col < width; col++) {
                    Tile tile = board.getTileAt(col, row);
                    if (tile != null) {
                        tile.setContainingBlock(null);
                    }
                }
                lineCleared = true;
                // Optionally make blocks above drop down
                shiftRowsDown(row);
            }
        }
        if (lineCleared) {
            updateBoard();
        }
    }

    public void spawnNewPiece() {
        BlockType blockType = getBlockType();
        int effectiveWidth = blockType.getEffectiveWidth();
        int boardWidth = board.getBoardWidth();

        Random rand = new Random();
        int spawnX = rand.nextInt(boardWidth - effectiveWidth + 1);
        int spawnY = 0;

        Block newBlock = new Block(blockType, spawnX, spawnY);
        boolean isBlockPlaced = newBlock.placeBlock(board);

        if (!isBlockPlaced) {
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

        boolean isBlockPlaced = currentPiece.placeBlock(board);

        if (!isBlockPlaced) {
            dropTimer.stop();
            currentPiece.removeBlock(board);
            currentPiece.getCentreOfMass().x -= posCount;
            currentPiece.placeBlock(board);

            checkLineClear();
            spawnNewPiece();
        } else {
            board.updateBoard();
        }
    }

    private void rotatePiece() {
        LShape newBlockType = ((LShape)(currentPiece.getBlockType())).getRotated();
        Block newBlock = new Block(newBlockType, currentPiece.getCentreOfMass().x, currentPiece.getCentreOfMass().y);
        currentPiece.removeBlock(board);
        currentPiece = newBlock;
        boolean isBlockPlaced = newBlock.placeBlock(board);
        if (!isBlockPlaced) {
            newBlock.removeBlock(board);
            currentPiece.placeBlock(board);
        }
        board.updateBoard();
    }


    private void dropPiece() {
        currentPiece.removeBlock(board);

        currentPiece.getCentreOfMass().y += 1;

        boolean isBlockPlaced = currentPiece.placeBlock(board);

        if (!isBlockPlaced) {
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
            gameOver = true;
        }
    }

    private void shiftRowsDown(int clearedRow) {
        // For Tetris row shifting logic, you remove the row, then shift every row above down by 1
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
