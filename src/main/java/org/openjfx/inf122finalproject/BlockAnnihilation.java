package org.openjfx.inf122finalproject;

import javafx.beans.property.ObjectProperty;

import java.util.ArrayList;

/** destroy a single block and replace it with empty block */
public class BlockAnnihilation {
    public static boolean perform(ArrayList<Position> positions, ObjectProperty<Block[][]> boardProperty, Tile[][] grids) {
        Block[][] blocks = boardProperty.get();
        for (Position pos : positions) {
            int row = pos.getRow();
            int column = pos.getColumn();
            Block block = blocks[row][column];
            if(block.getType() != BlockType.EMPTY_BLOCK) {
                blocks[row][column] = BlockFactory.createBlock(BlockType.EMPTY_BLOCK);
                grids[row][column].setContainsEmpty(true);
            }
        }
        boardProperty.set(null);
        boardProperty.set(blocks);
        return true;
    }
}
