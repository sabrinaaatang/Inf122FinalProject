package org.openjfx.inf122finalproject;

/**
 * Context class for executing tile manipulation strategies.
 * Uses the Strategy design pattern to allow dynamic assignment of different tile manipulations.
 */
public class TileManipulatorContext {
    private TileManipulator manipulator;

    /**
     * Constructs a TileManipulatorContext with a specific tile manipulation strategy.
     *
     * @param manipulator The initial tile manipulation strategy
     */
    public TileManipulatorContext(TileManipulator manipulator) {
        this.manipulator = manipulator;
    }

    /**
     * Sets a new tile manipulation strategy.
     *
     * @param manipulator The new tile manipulation strategy
     */
    public void setManipulator(TileManipulator manipulator) {
        this.manipulator = manipulator;
    }

    /**
     * Executes the currently assigned tile manipulation strategy.
     * If no strategy is assigned, the method does nothing.
     */
    public void executeManipulation() {
        if (manipulator != null) {
            manipulator.execute();
        }
    }
}
