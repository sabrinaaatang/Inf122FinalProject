package org.openjfx.inf122finalproject;

import javafx.scene.Node;
import javafx.scene.layout.VBox;

/* Make class Tile extend VBox, so Tile can perform as a container and can encapsulate our data */
/* Which means we can put blocks inside tiles using setUserData(Type of Block) */
public class Tile extends VBox {
    Position position;
    private Boolean[] walls = new Boolean[4];  // walls on direction N, E, S, W
    private String dropDirection;          // An auto dropable block in this tile will drop towards this direction;
    private boolean isPlaceable = true;

    /**
     * Create a tile container at Position(x = col, y = row).
     *
     * @param row The same as 'y' in class Position.
     * @param col The same as 'x' in class Position.
     */
    public Tile(int row, int col) {
        super();
        position = new Position(col, row);
    }

    /**
     * Create a tile container at Position(x = col, y = row).
     *
     * @param shape An element that can be contained inside the tile.
     * @param row The same as 'y' in class Position.
     * @param col The same as 'x' in class Position.
     */
    public Tile(Node shape, int row, int col) {
        super(shape);
        position = new Position(row, col);
    }

    public void setPlaceable(boolean placeable) {
        isPlaceable = placeable;
    }

    public boolean getPlaceable() {
        return isPlaceable;
    }
}
