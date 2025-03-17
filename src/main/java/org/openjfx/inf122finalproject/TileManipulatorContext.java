package org.openjfx.inf122finalproject;

public class TileManipulatorContext {
    private TileManipulator manipulator;

    public TileManipulatorContext(TileManipulator manipulator) {
        this.manipulator = manipulator;
    }

    public void setManipulator(TileManipulator manipulator) {
        this.manipulator = manipulator;
    }

    public void executeManipulation() {
        if (manipulator != null) {
            manipulator.execute();
        }
    }
}
