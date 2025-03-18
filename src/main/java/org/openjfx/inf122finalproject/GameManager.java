package org.openjfx.inf122finalproject;

public abstract class GameManager {
    public abstract boolean isGameOver();
    public abstract int getScore();
    public abstract void updateBoard();
    public abstract void handleInput(PlayerInput input);
}
