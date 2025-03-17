package org.openjfx.inf122finalproject;

public abstract class GameManager {
    public abstract boolean isGameOver();
    public abstract void updateBoard();
    public abstract void handleInput(PlayerInput input);
}
