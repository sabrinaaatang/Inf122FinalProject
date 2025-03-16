package org.openjfx.inf122finalproject;

public class BlockManipulatorContext {
    private BlockManipulator algorithm;

    public void setAlgorithm(BlockManipulator algorithm) {
        this.algorithm = algorithm;
    }
    public void performManipulation() {
        this.algorithm.manipulate();
    }
}
