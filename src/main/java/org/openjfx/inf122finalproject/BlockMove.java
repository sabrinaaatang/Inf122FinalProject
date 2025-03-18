package org.openjfx.inf122finalproject;

import javafx.beans.property.ObjectProperty;

/**
 *  Prepare manipulation for a single move (for two adjacent tiles) in the game board.
 */
public class BlockMove implements BlockManipulator {
    private final Board board;
    private final ObjectProperty<Block[][]> blocksBoard;
    private final Position ps;
    private final Position pd;

    /** move block on position1 to position2
     * @param board Get reference of Tiles for checking wall.
     * @param blocksBoard Has listener bind to this. Use to update UI.
     * @param ps  Start position.
     * @param pd Destination position.
     */
    public BlockMove(Board board, Position ps, Position pd, ObjectProperty<Block[][]> blocksBoard) {
        this.board = board;
        this.ps = ps;
        this.pd = pd;
        this.blocksBoard = blocksBoard;
    }

    /** assertion : all blocks inside property are filled either with Blocks or EmptyBlocks */
    public void manipulate() {
        Block[][] blocks = blocksBoard.get();
        Tile[][] tiles = board.getGrid();
        int r_st = ps.getRow();
        int c_st = ps.getColumn();
        int r_dest = pd.getRow();
        int c_dest = pd.getColumn();

        Block start = blocks[r_st][c_st];
        Block dest = blocks[r_dest][c_dest];

        /* check whether start block is an empty block */
        if(start.isEmpty) {
            System.out.println("cannot move a empty block" + start) ;
            return;
        }

        Position moveDir = Position.getVector(ps, pd);
        Position inverseDir = Position.inverse(moveDir);
        Tile startTile = tiles[r_st][c_st];
        Tile destTile = tiles[r_dest][c_dest];

        /* check if there is a wall in between */
        if(startTile.hasWallsOnDirection(moveDir) || destTile.hasWallsOnDirection(inverseDir)) {
            System.out.println("blocked by wall");
            return;
        }

        /* check whether end block is an empty block */
        if(!dest.isEmpty) {
            System.out.println("cannot move to an occupied place" + start) ;
            return;
        }

        /* now safe to move (swap) */
        blocks[r_st][c_st] = dest;
        blocks[r_dest][c_dest] = start;

        this.blocksBoard.set(null);
        this.blocksBoard.set(blocks);   //update property
    }
}
