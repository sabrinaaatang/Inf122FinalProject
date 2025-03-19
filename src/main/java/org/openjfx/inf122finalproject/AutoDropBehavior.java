package org.openjfx.inf122finalproject;

import javafx.beans.property.ObjectProperty;

public interface AutoDropBehavior {
    void autoDrop(ObjectProperty<Block[][]> blockProperty, Tile[][] grids, Position currentPosition);
}
