package org.openjfx.inf122finalproject;

import javafx.beans.property.ObjectProperty;

import java.util.ArrayList;

public class CandyBlockManipulator implements BlockManipulator {
    private Position p1;
    private final ObjectProperty<Block[][]> blockBoard;

    public CandyBlockManipulator(Position pos, ObjectProperty<Block[][]> blocksBoard) {
        this.blockBoard = blocksBoard;
        this.p1 = p1;
    }

    @Override
    public boolean blockMove() {
        return false;
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
        int r1 = p1.getRow();
        int r2 = p2.getRow();
        int c1 = p1.getColumn();
        int c2 = p2.getColumn();
        Block[][] b = blockBoard.get();
        // swap should be performed between two existing block
        if (b[r1][c1].isEmpty() || b[r2][c2].isEmpty() ) {
            System.out.println("Fail to swap because of empty block(s).");
            return false;
        }

        /* swap */
        Block block1 = b[r1][c1];
        b[r1][c1] = b[r2][c2];
        b[r2][c2] = block1;

        blockBoard.set(null);
        /* reset 2D block array to ObjectProperty to trigger event listener */
        blockBoard.set(b);
//        System.out.println(  b[r2][c2] + " | " + b[r1][c1] );
//        System.out.println( "Blocks at position " + p1 + ", " + p2 +  "Swapped blocks success.");
        return true;
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
