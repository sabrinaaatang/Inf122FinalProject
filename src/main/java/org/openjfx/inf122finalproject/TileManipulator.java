package org.openjfx.inf122finalproject;

/**
 * Interface for tile manipulation actions in the game.
 * Implementing classes should define specific tile transformations,
 * such as swapping or dropping tiles.
 */
public interface TileManipulator {
    /**
     * Executes the tile manipulation action.
     */
    void execute();
}
