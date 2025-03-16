package org.openjfx.inf122finalproject;

import javafx.scene.Node;
import javafx.scene.layout.VBox;

import java.util.*;

/* Make class Tile extends VBox, so Tile can perform as a container and can encapsulate our data */
/* Which means we can put blocks inside tiles using setUserData(Type of Block) */
public class Tile extends VBox {
    BoardPosition position;

    public Tile(int row, int col) {
        super();
        position = new BoardPosition(row, col);
    }

    public Tile(Node shape, int row, int col) {
        super(shape);
        position = new BoardPosition(row, col);
    }

}
