package org.openjfx.inf122finalproject;

import javafx.beans.property.ObjectProperty;

public class AutoDrop implements AutoDropBehavior
{
    /* tetris or candy auto drop */
    /** drop down */
    @Override
    public void autoDrop(ObjectProperty<Block[][]> blockProperty, Tile[][] tiles, Position currentPos) {
        final int row = tiles.length;  // 0 -> row-1
        final int col = tiles[0].length;    //0->col-1
        Block[][] blocks = blockProperty.get();
        int currRow = currentPos.getRow();
        int currCol = currentPos.getColumn();
        Block temp = blocks[currRow][currCol];
        int checkRow = currRow + 1;         /* check next row */

        while(checkRow < row)
        {
            Tile underTile = tiles[checkRow][currCol];
            if(!underTile.getContainsEmpty()) return;

            if(BlockMove.performMove(tiles, new Position(currCol, checkRow - 1),
                            new Position(currCol, checkRow), blockProperty))
            {
                checkRow++;
            } else {
                break;
            }
        }
        BlockMove.update(blockProperty);


    }
}
