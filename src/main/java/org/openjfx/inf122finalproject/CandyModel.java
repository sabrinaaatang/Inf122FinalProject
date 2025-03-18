package org.openjfx.inf122finalproject;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.Random;

public class CandyModel extends GameModel {
    /* Model Class
     * Function call to business logic and data access */
    private final CandyBoard candyBoard;
    private volatile ObjectProperty<Block[][]> blocks;     // create by the game manager?
//    private final CandyRule = new CandyGameRule();
    private final BlockManipulatorContext bm = new BlockManipulatorContext();

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
        bm.setAlgorithm(new BlockSwap(p1, p2, blocks));
        bm.performManipulation();
    }

    private void initBlocks() {
        this.blocks = new SimpleObjectProperty<>(new CandyBlock[getRow()][getColumn()]);
        Random rand = new Random();
        Block[][] candyBlocks= this.blocks.get();
        final BlockType[] blockTypes = {BlockType.PINK_CANDY, BlockType.CHOCO_CANDY, BlockType.BROWN_CANDY, BlockType.YELLOW_CANDY};
        for(int i = 0; i < getRow(); i++) {
            for(int j = 0; j < getColumn(); j++) {
                candyBlocks[i][j] = (CandyBlock) BlockFactory.createBlock(blockTypes[rand.nextInt(4)]);
            }
        }
        this.candyBlocksProperty().set(candyBlocks);
    }

}
