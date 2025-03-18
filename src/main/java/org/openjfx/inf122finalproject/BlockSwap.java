package org.openjfx.inf122finalproject;

import javafx.beans.property.ObjectProperty;

public class BlockSwap implements BlockManipulator {
    private Position p1;
    private Position p2;
    private final ObjectProperty<Block[][]>  blocksBoard;

    public BlockSwap(Position p1, Position p2, ObjectProperty<Block[][]> blocksBoard) {
        this.blocksBoard = blocksBoard;
        this.p1 = p1;
        this.p2 = p2;
    }


    public void manipulate() {
        int r1 = p1.getRow();
        int r2 = p2.getRow();
        int c1 = p1.getColumn();
        int c2 = p2.getColumn();
        Block[][] b = blocksBoard.get();
        // swap should be performed between two existing block
        if (b[r1][c1].isEmpty || b[r2][c2].isEmpty ) {
            System.out.println("Fail to swap because of empty block(s).");
            return;
        }

        /* swap */
        Block block1 = b[r1][c1];
        b[r1][c1] = b[r2][c2];
        b[r2][c2] = block1;

        blocksBoard.set(null);
        /* reset 2D block array to ObjectProperty to trigger event listener */
        blocksBoard.set(b);
        System.out.println("Swapped blocks success.");
    }

}
