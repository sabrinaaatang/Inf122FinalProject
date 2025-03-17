package org.openjfx.inf122finalproject;

public class TileMove implements TileManipulator {
    private Board board;
    private int x1, y1;
    private int x2, y2;

    public TileMove(Board board, int x1, int y1, int x2, int y2) {
        this.board = board;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    @Override
    public void execute() {
        Tile sourceTile = board.getTileAt(x1, y1);
        Tile targetTile = board.getTileAt(x2, y2);
        if (sourceTile == null || targetTile == null) return;
        if (targetTile.getContainingBlock() != null) return; // target must be empty for a move
        Block blockToMove = sourceTile.getContainingBlock();
        targetTile.setContainingBlock(blockToMove);
        sourceTile.setContainingBlock(null);
    }
}
