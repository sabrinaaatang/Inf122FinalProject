package org.openjfx.inf122finalproject;

import javafx.animation.PauseTransition;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Pos;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Random;

public class CandyModel extends GameModel {
    /* Model Class
     * Function call to business logic and data access */
    private final CandyBoard candyBoard;
    private volatile ObjectProperty<Block[][]> blocks;     // create by the game manager?
//    private final CandyRule = new CandyGameRule();


    public CandyModel(int row, int col) {
        super(row, col);
        this.setBoard(new CandyBoard(row, col));
        candyBoard = (CandyBoard)this.getBoard();
        initBlocks();
    }

    public Tile[][] getGrid() { return candyBoard.getGrid(); }

    public ObjectProperty<Block[][]> candyBlocksProperty() { return this.blocks; }

    /* block manipulation */
    public void trySwap(Position p1, Position p2) {
        PauseTransition pause = new PauseTransition(Duration.seconds(0.3)); // 0.5-second delay

        Block[][] blockBoard = blocks.get();
        ArrayList<Position> positions = new ArrayList<>();
        if(BlockSwap.performSwap(p1, p2, blocks)) {
            System.out.println("Manipulation performed!");
            ArrayList<ArrayList<Position>> result = new ArrayList<>();
            if(p1.isAbove(p2))
                result = CandyBoardChecker.upDownChecker(blockBoard, p1, p2);
            else if(p1.isUnder(p2))
                result = CandyBoardChecker.upDownChecker(blockBoard, p2, p1);
            else if(p1.isBehind(p2))
                result = CandyBoardChecker.leftRightChecker(blockBoard, p1, p2);
            else if(p1.isAhead(p2))
                result = CandyBoardChecker.leftRightChecker(blockBoard, p2, p1);

            positions = CandyBoardChecker.flattener(result);
            if(!positions.isEmpty()) {
                final ArrayList<Position> pos = positions;
                pause.setOnFinished(event ->
                    // Perform candy removal after the delay
                    BlockAnnihilation.perform(pos, blocks, candyBoard.getGrid())
                );
                pause.play();
            }
            else
                BlockSwap.performSwap(p1, p2, blocks);

        }
    }

    public void checkMatchAfterSwap(Block[][] blocks, Position p1, Position p2) {}

    private void initBlocks() {
        this.blocks = new SimpleObjectProperty<>(new Block[getRow()][getColumn()]);
        Random rand = new Random();
        Block[][] candyBlocks= this.blocks.get();
        Tile[][] tiles = this.candyBoard.getGrid();
        final BlockType[] blockTypes = {BlockType.PINK_CANDY, BlockType.CHOCO_CANDY, BlockType.BROWN_CANDY,
                BlockType.YELLOW_CANDY, BlockType.ICECREAM_CANDY, BlockType.REAL_CANDY, BlockType.CREAM_CANDY};
        for(int i = 0; i < getRow(); i++) {
            for(int j = 0; j < getColumn(); j++) {
                Block candyBlock = BlockFactory.createBlock(blockTypes[rand.nextInt(blockTypes.length)]);
                candyBlock.addTile(tiles[i][j]);
                tiles[i][j].setContainsEmpty(false);
                candyBlocks[i][j] = candyBlock;
            }
        }
        this.candyBlocksProperty().set(candyBlocks);
    }

//    public void refillBoard() {
//        int boardWidth = candyBoard.getWidth();
//        int boardHeight = candyBoard.getHeight();
//
//        // Let blocks fall down
//        for (int col = 0; col < boardWidth; col++) {
//            for (int row = boardHeight - 1; row >= 0; row--) {
//                if (board.getTileAt(col, row).getContainingBlock() == null) {
//                    // Move upward tiles down
//                    for (int aboveRow = row - 1; aboveRow >= 0; aboveRow--) {
//                        Tile aboveTile = board.getTileAt(col, aboveRow);
//                        if (aboveTile.getContainingBlock() != null) {
//                            board.getTileAt(col, row).setContainingBlock(aboveTile.getContainingBlock());
//                            aboveTile.setContainingBlock(null);
//                            break;
//                        }
//                    }
//                }
//            }
//        }
//
//        // Fill empty top rows with new random blocks
//        for (int col = 0; col < boardWidth; col++) {
//            for (int row = 0; row < boardHeight; row++) {
//                Tile tile = board.getTileAt(col, row);
//                if (tile.getContainingBlock() == null) {
//                    BlockType newType = getBlockType();
//                    Block block = new Block(newType, col, row);
//                    block.addTile(tile);
//                    tile.setContainingBlock(block);
//                }
//            }
//        }
//        board.updateBoard();
//    }




    public Block getBlock(Position p) {
        return this.blocks.get()[p.getRow()][p.getColumn()];
    }

}
