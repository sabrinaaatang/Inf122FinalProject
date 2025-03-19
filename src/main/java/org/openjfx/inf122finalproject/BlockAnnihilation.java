package org.openjfx.inf122finalproject;

import javafx.beans.property.ObjectProperty;

import java.util.ArrayList;

/** destroy a single block and replace it with empty block */
public class BlockAnnihilation {
    public static synchronized boolean perform(ArrayList<Position> positions, ObjectProperty<Block[][]> boardProperty, Tile[][] grids) {
        Block[][] blocks = boardProperty.get();
        for (Position pos : positions) {
            int row = pos.getRow();
            int column = pos.getColumn();
            Block block = blocks[row][column];
            if(block.getType() != BlockType.EMPTY_BLOCK) {
                System.out.println(block + " set to empty_block");
                blocks[row][column] = BlockFactory.createBlock(BlockType.EMPTY_BLOCK);
                grids[row][column].setContainsEmpty(true);
            }
        }

        boardProperty.set(null);
        boardProperty.set(blocks);

//        for(int r = 0; r < grids.length; r++)   // the last row will not drop in any cases
//        {
//            for (int c = 0; c < grids[0].length; c++) {
//                Block candyBlock = boardProperty.get()[r][c];
//                System.out.println( "// " + candyBlock);
////                candyBlock.autoDropBehavior.autoDrop(blocks, tiles, new Position(c, r));
//            }
//        }

        return true;
    }
}
