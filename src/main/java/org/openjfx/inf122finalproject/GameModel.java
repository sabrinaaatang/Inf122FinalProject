package org.openjfx.inf122finalproject;

public abstract class GameModel {
    private int row;
    private int column;
    private Board board;

    GameModel(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Board getBoard() {
        return board;
    }
}
