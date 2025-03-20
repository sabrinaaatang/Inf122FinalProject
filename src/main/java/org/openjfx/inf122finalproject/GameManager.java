package org.openjfx.inf122finalproject;

/**
 * Abstract class representing a game manager.
 * Defines core methods for managing game state, handling input, and updating the board.
 */
public abstract class GameManager {

    /**
     * Determines whether the game is over.
     *
     * @return true if the game is over, false otherwise
     */
    public abstract boolean isGameOver();

    /**
     * Retrieves the current game score.
     *
     * @return The player's score
     */
    public abstract int getScore();

    /**
     * Updates the game board by processing any changes such as clearing matches
     * or moving pieces.
     */
    public abstract void updateBoard();

    /**
     * Handles player input such as mouse clicks or key presses.
     *
     * @param input The player input event to be processed
     */
    public abstract void handleInput(PlayerInput input);

    /**
     * Sets a callback to be executed when the game is over.
     * Can be overridden by subclasses to define specific behavior.
     *
     * @param callback A Runnable function to execute when the game ends
     */
    public void setGameOverCallback(Runnable callback) {}
}
