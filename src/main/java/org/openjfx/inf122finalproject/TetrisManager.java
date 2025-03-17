package org.openjfx.inf122finalproject;
import java.util.Random;

public class TetrisManager extends GameManager {
    private TileManipulatorContext manipulator;
    private Board board;
    public TetrisManager(Board board) {
        this.board = board;
        Initialize();
    }

    private void Initialize() {

    }

    public void checkLineClear() {}

    public void spawnNewPiece() {
    }

    @Override
    public boolean isGameOver() {
        return false;
    }

    @Override
    public void updateBoard() {
    }

    @Override
    public void handleInput(PlayerInput input) {

    }
}
