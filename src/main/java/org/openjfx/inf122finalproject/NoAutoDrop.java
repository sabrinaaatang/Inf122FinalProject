package org.openjfx.inf122finalproject;

import javafx.beans.property.ObjectProperty;

public class NoAutoDrop implements AutoDropBehavior {

    @Override
    public void autoDrop(ObjectProperty<Block[][]> blockProperty, Tile[][] grids) {
        /* can't auto drop */
    }
}
