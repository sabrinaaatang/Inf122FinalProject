package org.openjfx.inf122finalproject;


public class CandyCrushManager extends GameManager {
    private TileManipulatorContext manipulatorContext;
    private final Board board;
    private int minClearCount = 3;
    public CandyCrushManager(Board board) {
        this.board = board;
        fillEmptyBlocks();
    }

    @Override
    public void updateBoard() {
        while(true){
            checkMatchesAndClear();
            compactAllColumns();
            boolean newBlocksAdded = fillEmptyBlocks();
            if(!newBlocksAdded){
                break;
            }
        }
    }
    public void compactAllColumns() {
        int boardWidth = board.getBoardWidth();
        int boardHeight = board.getBoardHeight();
        for (int col = 0; col < boardWidth; col++) {
            compactOneColumn(col, boardHeight);
        }
    }
    public boolean compactOneColumn(int columnIndex, int rowCount) {
        boolean isCompacted = false;
        for (int row = rowCount - 1; row > 0; row--) {
            Tile currentTile = board.getTileAt(columnIndex, row);
            if (currentTile.getContainingBlock() == null) {
                for (int r = row - 1; r >= 0; r--) {
                    Tile aboveTile = board.getTileAt(columnIndex, r);
                    if (aboveTile.getContainingBlock() != null) {
                        currentTile.setContainingBlock(aboveTile.getContainingBlock());
                        aboveTile.setContainingBlock(null);
                        isCompacted = true;
                        break;
                    }
                }
            }
        }
        return isCompacted;
    }
    private boolean fillEmptyBlocks() {
        boolean newBlocksAdded = false;
        int boardWidth = board.getBoardWidth();
        int boardHeight = board.getBoardHeight();
        for (int col = 0; col < boardWidth; col++) {
            boolean placed = true;
            while (placed) {
                BlockType blockType = BlockFactory.createBlock("Candy");
                Block block = new Block(blockType, col, 0);
                placed = block.placeBlock(board);
                if (placed) {
                    newBlocksAdded = true;
                    compactOneColumn(col, boardHeight);
                }
            }
        }
        return newBlocksAdded;
    }
    @Override
    public void handleInput(PlayerInput input) {
        if (input.getSource() == PlayerInput.InputSource.MOUSE) {
            int tileSize = board.getTileSize();
            int startTileX = (int) (input.getStartX() / tileSize);
            int startTileY = (int) (input.getStartY() / tileSize);
            int endTileX = (int) (input.getEndX() / tileSize);
            int endTileY = (int) (input.getEndY() / tileSize);

            int targetX = 0;
            int targetY = 0;
            boolean executeAction = false;
            if (Math.abs(endTileX - startTileX) == 1 && (endTileY - startTileY) == 0) {//Horizonal drag
                targetX = endTileX;
                targetY = startTileY;
                executeAction = true;
            } else if ((endTileX - startTileX) == 0 && Math.abs(endTileY - startTileY) == 1) {//Vertical drag
                targetX = startTileX;
                targetY = endTileY;
                executeAction = true;
            }
            if (executeAction) {
                TileManipulator manipulator = new TileSwap(board, startTileX, startTileY, targetX, targetY);
                manipulator.execute();
            }
        }
       this.updateBoard();
    }
    public void checkMatchesAndClear() {
        while (true) {
            boolean[][] toClear = checkMatches();
            if (!anyMatches(toClear)) {
                break;
            }
            clearMatches(toClear);
        }
    }
    private boolean anyMatches(boolean[][] arr) {
        for (boolean[] row : arr) {
            for (boolean b : row) {
                if (b) {
                    return true;
                }
            }
        }
        return false;
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
        for (int row = 0; row < boardHeight; row++) {
            for (int col = 0; col < boardWidth; col++) {
                if (toClear[row][col]) {
                    Tile tile = board.getTileAt(col, row);
                    if (tile != null) {
                        tile.setContainingBlock(null);
                    }
                }
            }
        }

    }
    private boolean blocksMatch(Tile t1, Tile t2) {
        if (t1 == null || t2 == null) return false;
        if (t1.getContainingBlock() == null || t2.getContainingBlock() == null) return false;
        return t1.getContainingBlock().getBlockType().getColor()
                .equals(t2.getContainingBlock().getBlockType().getColor());
    }
    @Override
    public boolean isGameOver() {
        return false;
    }
}
