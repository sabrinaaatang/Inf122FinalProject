package org.openjfx.inf122finalproject;

public class Position {
    private final int x;              // →
    private final int y;              // ↓

    /** Beware of directions
     * @param x → count right, col #
     * @param y ↓ count down, row # */
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

    /** return y */
    public int getRow() {
        return y;
    }

    /** return x */
    public int getColumn() {
        return x;
    }

    public static Position getVector(Position start, Position dest) {
        return new Position(dest.x - start.x, dest.y - start.y);
    }

    public static Position inverse(Position vec) {
        return new Position(-vec.x, -vec.y);
    }

    public Position getUpPos() {
        return new Position(x , y - 1);
    }
    public Position getDownPos() {
        return new Position(x , y + 1);
    }
    public Position getLeftPos() {
        return new Position(x - 1, y);
    }
    public Position getRightPos() {
        return new Position(x + 1, y);
    }
    public Position nextPos(Position Direction) {
        return new Position(x + Direction.x, y + Direction.y);
    }

    /** (0, -1) */
    public static Position upDirection() {
        return new Position(0, -1);
    }
    /** (0, 1) */
    public static Position downDirection() {
        return new Position(0, 1);
    }
    /** (-1, 0) */
    public static Position leftDirection() {
        return new Position(-1, 0);
    }
    /** (1, 0) */
    public static Position rightDirection() {
        return new Position(1, 0);
    }

    /** x the same but y < B.y */
    public boolean isAbove(Position B) {
        return this.x == B.x && this.y < B.y;
    }
    /** x the same but y < B.y */
    public boolean isUnder(Position B) {
        return this.x == B.x && this.y > B.y;
    }
    /** if y are the same but x < B.x */
    public boolean isBehind(Position B) {
        return this.y == B.y && this.x < B.x;
    }
    /** if y are the same but x < B.x */
    public boolean isAhead(Position B) {
        return this.y == B.y && this.x > B.x;
    }

    @Override
    public String toString() {
        return "(x: " + this.x + ", y: " + this.y + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

}
