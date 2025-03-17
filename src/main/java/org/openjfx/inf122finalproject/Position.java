package org.openjfx.inf122finalproject;

public class Position {
    int x;
    int y;

    /* Beware of  */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isNextTo(Position pos, int maxX, int maxY) {
        if (this.x == pos.x ) {
            if (this.y != 0 && this.y - 1 == pos.y) return true;

            if(this.y != maxY && this.y + 1 == pos.y) return true;

        }
        if (this.y == pos.y) {
            if (this.x != 0 && this.x - 1 == pos.x) return true;

            if (this.x != maxX && this.x + 1 == pos.x) return true;

        }
        return false;
    }


}
