package org.openjfx.inf122finalproject;

import java.util.ArrayList;

public interface BlockManipulator {
    /** return boolean since manipulation might fail */
    boolean blockMove(Position dest);

    boolean blockRotate();

    boolean blockAutoDrop();

    boolean blockSwap(Position swapWith);

    boolean blockDrop();

    boolean blockAnnihilation(ArrayList<Position> positions);
}
