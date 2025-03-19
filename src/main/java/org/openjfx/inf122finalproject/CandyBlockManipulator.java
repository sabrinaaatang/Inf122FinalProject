package org.openjfx.inf122finalproject;

import javafx.beans.property.ObjectProperty;

import java.util.ArrayList;

public class CandyBlockManipulator implements BlockManipulator {
    private Position currentPos;
    private final ObjectProperty<Block[][]> blockBoard;
    private Board gridBoard;

    public CandyBlockManipulator(Position pos, ObjectProperty<Block[][]> blocksBoard, Board gridBoard) {
        this.blockBoard = blocksBoard;
        this.currentPos = pos;
        this.gridBoard = gridBoard;
    }

    @Override
    public boolean blockMove(Position dest) {
        return BlockMove.performMove(this.gridBoard.getGrid(), currentPos, dest, blockBoard);
    }

    @Override
    public boolean blockRotate() {
        return false;
    }

    @Override
    public boolean blockAutoDrop() {
        return false;
    }

    @Override
    public boolean blockSwap(Position p2) {
        return BlockSwap.performSwap(currentPos, p2, blockBoard);
    }

    @Override
    public boolean blockDrop() {
        return false;
    }

    @Override
    public boolean blockAnnihilation(ArrayList<Position> positions) {
        Block[][] blocks = this.blockBoard.get();
        for (Position pos : positions) {
            Block block = blocks[pos.getRow()][pos.getColumn()];
            if(block.getType() != BlockType.EMPTY_BLOCK)
                blocks[pos.getRow()][pos.getColumn()] = BlockFactory.createBlock(BlockType.EMPTY_BLOCK);
        }
        blockBoard.set(null);
        blockBoard.set(blocks);
        return true;
    }
}
