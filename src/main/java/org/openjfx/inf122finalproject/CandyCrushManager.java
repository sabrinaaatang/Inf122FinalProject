package org.openjfx.inf122finalproject;

public class CandyCrushManager extends GameManager {
    private TileManipulatorContext manipulator;
    private Board board;
    private int minClearCount = 3; // Number of adjacent matches required

    public CandyCrushManager(Board board) {
        this.board = board;
        fillBoard();
    }

    public boolean[][] checkMatches() {
        int boardWidth = board.getBoardWidth();
        int boardHeight = board.getBoardHeight();
        boolean[][] toClear = new boolean[boardHeight][boardWidth];

        // Horizontal check
        for (int row = 0; row < boardHeight; row++) {
            int count = 1;
            for (int col = 1; col < boardWidth; col++) {
                Tile prev = board.getTileAt(col - 1, row);
                Tile current = board.getTileAt(col, row);
                if (blocksMatch(prev, current)) {
                    count++;
                } else {
                    if (count >= minClearCount) {
                        for (int c = col - count; c < col; c++) {
                            toClear[row][c] = true;
                        }
                    }
                    count = 1;
                }
            }
            if (count >= minClearCount) {
                for (int c = boardWidth - count; c < boardWidth; c++) {
                    toClear[row][c] = true;
                }
            }
        }

        // Vertical check
        for (int col = 0; col < boardWidth; col++) {
            int count = 1;
            for (int row = 1; row < boardHeight; row++) {
                Tile prev = board.getTileAt(col, row - 1);
                Tile current = board.getTileAt(col, row);
                if (blocksMatch(prev, current)) {
                    count++;
                } else {
                    if (count >= minClearCount) {
                        for (int r = row - count; r < row; r++) {
                            toClear[r][col] = true;
                        }
                    }
                    count = 1;
                }
            }
            if (count >= minClearCount) {
                for (int r = boardHeight - count; r < boardHeight; r++) {
                    toClear[r][col] = true;
                }
            }
        }

        return toClear;
    }

    public void clearMatches(boolean[][] toClear) {
        int boardWidth = board.getBoardWidth();
        int boardHeight = board.getBoardHeight();
        boolean cleared = false;

        for (int row = 0; row < boardHeight; row++) {
            for (int col = 0; col < boardWidth; col++) {
                if (toClear[row][col]) {
                    Tile tile = board.getTileAt(col, row);
                    if (tile != null) {
                        tile.setContainingBlock(null);
                        cleared = true;
                    }
                }
            }
        }
        if (cleared) {
            board.updateBoard();
        }
    }

    public void refillBoard() {
        int boardWidth = board.getBoardWidth();
        int boardHeight = board.getBoardHeight();

        // Let blocks fall down
        for (int col = 0; col < boardWidth; col++) {
            for (int row = boardHeight - 1; row >= 0; row--) {
                if (board.getTileAt(col, row).getContainingBlock() == null) {
                    // Move upward tiles down
                    for (int aboveRow = row - 1; aboveRow >= 0; aboveRow--) {
                        Tile aboveTile = board.getTileAt(col, aboveRow);
                        if (aboveTile.getContainingBlock() != null) {
                            board.getTileAt(col, row).setContainingBlock(aboveTile.getContainingBlock());
                            aboveTile.setContainingBlock(null);
                            break;
                        }
                    }
                }
            }
        }

        // Fill empty top rows with new random blocks
        for (int col = 0; col < boardWidth; col++) {
            for (int row = 0; row < boardHeight; row++) {
                Tile tile = board.getTileAt(col, row);
                if (tile.getContainingBlock() == null) {
                    BlockType newType = getBlockType();
                    Block block = new Block(newType, col, row);
                    block.addTile(tile);
                    tile.setContainingBlock(block);
                }
            }
        }
        board.updateBoard();
    }

    private void fillBoard() {
        int boardWidth = board.getBoardWidth();
        int boardHeight = board.getBoardHeight();

        for (int row = 0; row < boardHeight; row++) {
            for (int col = 0; col < boardWidth; col++) {
                Tile tile = board.getTileAt(col, row);
                if (tile != null) {
                    BlockType blockType = getBlockType();
                    Block block = new Block(blockType, col, row);
                    block.addTile(tile);
                    tile.setContainingBlock(block);
                }
            }
        }
        board.updateBoard();
    }

    private boolean blocksMatch(Tile t1, Tile t2) {
        if (t1 == null || t2 == null) return false;
        if (t1.getContainingBlock() == null || t2.getContainingBlock() == null) return false;
        return t1.getContainingBlock().getBlockType().getColor()
                .equals(t2.getContainingBlock().getBlockType().getColor());
    }

    private BlockType getBlockType() {
        return BlockFactory.createBlock("Candy");
    }

    @Override
    public boolean isGameOver() {
        return false;
    }

    @Override
    public void updateBoard() {
        board.updateBoard();
    }

    @Override
    public void handleInput(PlayerInput input) {
        if (input.getSource() == PlayerInput.InputSource.MOUSE) {
            int tileSize = board.getTileSize();
            int startTileX = (int) (input.getStartX() / tileSize);
            int startTileY = (int) (input.getStartY() / tileSize);
            int endTileX = (int) (input.getEndX() / tileSize);
            int endTileY = (int) (input.getEndY() / tileSize);

            int deltaX = endTileX - startTileX;
            int deltaY = endTileY - startTileY;

            int targetX = 0;
            int targetY = 0;
            boolean executeAction = false;
            if (Math.abs(deltaX) == 1 && deltaY == 0) {
                targetX = startTileX + deltaX;
                targetY = startTileY;
                executeAction = true;
            } else if (Math.abs(deltaY) == 1 && deltaX == 0) {// Vertical move
                targetX = startTileX;
                targetY = startTileY + deltaY;
                executeAction = true;
            }
            if(executeAction){
                Tile targetTile = board.getTileAt(targetX, targetY);
                if (targetTile != null && targetTile.getContainingBlock() != null) {
                    TileSwap swap = new TileSwap(board, startTileX, startTileY, targetX, targetY);
                    swap.execute();
                } else {
                    TileMove move = new TileMove(board, startTileX, startTileY, targetX, targetY);
                    move.execute();
                }
            }
        }
        boolean[][] toClear = checkMatches();
        clearMatches(toClear);
        refillBoard();
        chainReactionClear();
    }


    private void chainReactionClear() {
        while (true) {
            boolean[][] toClear = checkMatches();
            if (!anyTrue(toClear)) break;
            clearMatches(toClear);
            refillBoard();
        }
    }

    private boolean anyTrue(boolean[][] arr) {
        for (boolean[] booleans : arr) {
            for (boolean aBoolean : booleans) {
                if (aBoolean) {
                    return true;
                }
            }
        }
        return false;
    }


}
