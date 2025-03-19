package org.openjfx.inf122finalproject;

import javafx.beans.property.ObjectProperty;

public class NoAutoDrop implements AutoDropBehavior {

    @Override
    public void autoDrop(ObjectProperty<Block[][]> blockProperty, Tile[][] grids, Position currentPos) {
        /* can't auto drop */
        System.out.println("No AutoDrop is called");
    }
}
