package org.openjfx.inf122finalproject;

import javafx.scene.paint.Color;

public class TileSwap implements TileManipulator {
    private Board board;
    private int x1, y1;
    private int x2, y2;

    public TileSwap(Board board, int x1, int y1, int x2, int y2) {
        this.board = board;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    @Override
    public void execute() {
        Tile tile1 = board.getTileAt(x1, y1);
        Tile tile2 = board.getTileAt(x2, y2);
        if (tile1 == null || tile2 == null) return;
        Block block1 = tile1.getContainingBlock();
        Block block2 = tile2.getContainingBlock();
        tile1.setContainingBlock(block2);
        tile2.setContainingBlock(block1);
    }
}
