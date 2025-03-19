package org.openjfx.inf122finalproject;

    public class CandyCrushManager extends GameManager {
        private final Board board;

        private ScoreManager scoreManager;
        private Player player;
        private int minClearCount = 3;

        private final int POINTS_PER_TILE = 10;

        public CandyCrushManager(Board board) {
            this.board = board;
            this.scoreManager = new ScoreManager();
            this.player = new Player(1, "Player 1");
            fillEmptyBlocks();
        }

        @Override
        public void updateBoard() {
            while (true) {
                checkMatchesAndClear();
                compactAllColumns();
                boolean newBlocksAdded = fillEmptyBlocks();
                if (!newBlocksAdded) {
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

                if (Math.abs(endTileX - startTileX) == 1 && endTileY == startTileY) {
                    targetX = endTileX;
                    targetY = startTileY;
                    executeAction = true;
                } else if (endTileX == startTileX && Math.abs(endTileY - startTileY) == 1) {
                    targetX = startTileX;
                    targetY = endTileY;
                    executeAction = true;
                }

                if (executeAction) {
                    TileManipulator manipulator = new TileSwap(board, startTileX, startTileY, targetX, targetY);
                    manipulator.execute(); // animate swap

                    // delay before checking the validity of the move
                    int finalTargetX = targetX;
                    int finalTargetY = targetY;
                    new Thread(() -> {
                        try {
                            Thread.sleep(300); // delay for animation
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        if (isValidSwap(startTileX, startTileY, finalTargetX, finalTargetY)) {
                            updateBoard(); // keep the swap and update the board if it is a valid match
                        } else {
                            // reverse the swap with animation for invalid move
                            TileManipulator reverseManipulator = new TileSwap(board, startTileX, startTileY, finalTargetX, finalTargetY);
                            reverseManipulator.execute();
                        }
                    }).start();
                }
            }
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

            // Horizontal match checking
            for (int row = 0; row < boardHeight; row++) {
                int count = 1;
                for (int col = 1; col < boardWidth; col++) {
                    Tile prev = board.getTileAt(col - 1, row);
                    Tile current = board.getTileAt(col, row);

                    if (blocksMatch(prev, current)) {
                        count++;
                    } else {
                        if (count >= minClearCount) {
                            System.out.println("[MATCH] Found horizontal match of " + count + " at row " + row + " from col " + (col - count) + " to " + (col - 1));
                            for (int c = col - count; c < col; c++) {
                                toClear[row][c] = true;
                            }
                        }
                        count = 1;
                    }
                }
                // Ensure last group is checked
                if (count >= minClearCount) {
                    System.out.println("[MATCH] Found horizontal match of " + count + " at row " + row + " from col " + (boardWidth - count) + " to " + (boardWidth - 1));
                    for (int c = boardWidth - count; c < boardWidth; c++) {
                        toClear[row][c] = true;
                    }
                }
            }

            // Vertical match checking
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
                // Ensure last group is checked
                if (count >= minClearCount) {
                    for (int r = boardHeight - count; r < boardHeight; r++) {
                        toClear[r][col] = true;
                    }
                }
            }
            return toClear;
        }


        public void clearMatches(boolean[][] toClear) {
            int tilesCleared = 0;
            for (int row = 0; row < board.getBoardHeight(); row++) {
                for (int col = 0; col < board.getBoardWidth(); col++) {
                    if (toClear[row][col]) {
                        Tile tile = board.getTileAt(col, row);
                        if (tile != null) {
                            tile.setContainingBlock(null);
                            tilesCleared++;
                        }
                    }
                }
            }
            if (tilesCleared > 0) {
                int pointsEarned = tilesCleared * POINTS_PER_TILE;
                scoreManager.updateScore(player, pointsEarned);
            }
        }

        private boolean blocksMatch(Tile t1, Tile t2) {
            if (t1 == null || t2 == null) {
                return false;
            }

            if (t1.getContainingBlock() == null || t2.getContainingBlock() == null) {
                return false;
            }

            String type1 = t1.getContainingBlock().getBlockType().getTypeIdentifier();
            String type2 = t2.getContainingBlock().getBlockType().getTypeIdentifier();

            return type1.equals(type2);
        }

        @Override
    public boolean isGameOver() {
        return false;
    }

    @Override
    public int getScore() {
        return scoreManager.getScore(player);
    }

    private boolean isValidSwap(int x1, int y1, int x2, int y2) {boolean[][] matches = checkMatches();
            return anyMatches(matches); // swap is valid only if a match is found
         }

    }
