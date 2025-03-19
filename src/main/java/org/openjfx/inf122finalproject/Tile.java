package org.openjfx.inf122finalproject;

import javafx.scene.Node;
import javafx.scene.layout.VBox;

/* Make class Tile extend VBox, so Tile can perform as a container and can encapsulate our data */
/* Which means we can put blocks inside tiles using setUserData(Type of Block) */
public class Tile extends VBox {
    private Position position;
    private boolean[] walls = new boolean[4];  // walls on direction N, E, S, W
    private Position dropDirection = new Position(0, 1);   // Downward. An auto dropable block in this tile will drop towards this direction;
    private boolean isPlaceable = true;
    private boolean containsEmpty = true;

    /**
     * Create a tile container at Position(x = col, y = row).
     * Default drop direction to a vector-> (0, 1), meaning that when it drops the y-axis or #row increase by 1,
     *
     * @param row The same as 'y' in class Position.
     * @param col The same as 'x' in class Position.
     */
    public Tile(int row, int col) {
        super();
        position = new Position(col, row);
        dropDirection = new Position(0, -1);

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

    public void setDropDirection(Position dropDirection) {
        this.dropDirection = dropDirection;
    }

    public void setContainsEmpty(boolean containsEmpty) {
        this.containsEmpty = containsEmpty;
    }

    public boolean getPlaceable() {
        return isPlaceable;
    }

    public Position getPosition() {
        return position;
    }


    public Position getDropDirection() {
        return dropDirection;
    }

    public boolean[] getWalls() {
        return walls;
    }
    public boolean getContainsEmpty() {
        return containsEmpty;
    }

    public void addWalls(String direction) {
        switch (direction) {
            case "N" -> this.walls[0] = true;
            case "E" -> this.walls[1] = true;
            case "S" -> this.walls[2] = true;
            case "W" -> this.walls[3] = true;
        }
    }

    public void removeWalls(String direction) {
        switch (direction) {
            case "N" -> this.walls[0] = false;
            case "E" -> this.walls[1] = false;
            case "S" -> this.walls[2] = false;
            case "W" -> this.walls[3] = false;
        }
    }

    public void clearWall() { this.walls = new boolean[4]; }

    public boolean hasWallsOnDirection(Position direction) {
        int y = direction.getRow();
        int x = direction.getColumn();
        if     ( x == 0 && y > 0) { return walls[0]; }
        else if(x > 0 && y == 0) { return walls[1]; }
        else if(x == 0 && y < 0) { return walls[2]; }
        else if(x < 0 && y == 0) { return walls[3]; }
        else {
            System.out.println("Invalid direction");
            return false;
        }
    }

}
