package org.openjfx.inf122finalproject;

import javafx.beans.property.ObjectProperty;

/**
 *  Prepare manipulation for a single move (for two adjacent tiles) in the game board.
 */
public class BlockMove {


    /**
     * assertion : all blocks inside property are filled either with Blocks or EmptyBlocks.
     * Function : Move block on position1 to empty position2
     *
     * @param tiles       Reference of Tiles for checking wall.
     * @param blocksBoard Has listener bind to this. Use to update UI.
     * @param ps          Start position.
     * @param pd          Destination position.
     */
    public static boolean performMoveWithoutTriggerListener(Tile[][] tiles, Position ps, Position pd, ObjectProperty<Block[][]> blocksBoard) {
        Block[][] blocks = blocksBoard.get();
        int r_st = ps.getRow();
        int c_st = ps.getColumn();
        int r_dest = pd.getRow();
        int c_dest = pd.getColumn();

        Block start = blocks[r_st][c_st];
        Block dest = blocks[r_dest][c_dest];

        /* check whether start block is an empty block */
        if (start.isEmpty()) {
            System.out.println("cannot move a empty block" + start);
            return false;
        }

        Position moveDir = Position.getVector(ps, pd);
        Position inverseDir = Position.inverse(moveDir);
        Tile startTile = tiles[r_st][c_st];
        Tile destTile = tiles[r_dest][c_dest];

        /* check if there is a wall in between */
        if (startTile.hasWallsOnDirection(moveDir) || destTile.hasWallsOnDirection(inverseDir)) {
            System.out.println("blocked by wall");
            return false;
        }

        /* check whether end block is an empty block */
        if (!dest.isEmpty()) {
            System.out.println("block: " + start + " at " + ps +  " " );
            System.out.println("cannot move to an occupied place" + dest + " at " + pd);
            return false;
        }

        /* now safe to move (swap) */
        blocks[r_st][c_st] = dest;
        blocks[r_dest][c_dest] = start;

        start.removeTile(startTile);
        start.addTile(destTile);
        dest.removeTile(destTile);
        dest.addTile(startTile);

        tiles[r_st][c_st].setContainsEmpty(dest.isEmptyType());
        tiles[r_dest][c_dest].setContainsEmpty(start.isEmptyType());
        System.out.println("moved " + start + " to " + dest + " at " + pd + " succeed!");

        blocksBoard.set(blocks);   //update property
        return true;
    }

    public static boolean performMove(Tile[][] tiles, Position ps, Position pd, ObjectProperty<Block[][]> blocksBoard) {
        boolean success = performMoveWithoutTriggerListener(tiles, ps, pd, blocksBoard);
        update(blocksBoard);
        return success;
    }

    public static void update(ObjectProperty<Block[][]> blocksBoard) {
        Block[][] blocks = blocksBoard.get();
        blocksBoard.set(null);
        blocksBoard.set(blocks);
    }
}
