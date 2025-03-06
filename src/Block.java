import java.util.ArrayList;
import java.awt.Point;

public abstract class Block {
    ArrayList<Tile> tiles;
    String name;
    Point centreOfMass;

    public Block(String name) {
        this.name = name;
        tiles = new ArrayList<>();
        centreOfMass = new Point(0, 0);
    }

    public void placeBlock(Board board) {
        //
    }

    public void removeBlock(Board board) {
        //
    }

    // more functions

    @Override
    public String toString() {
        return "A " + this.name + " Block";
    }
}
