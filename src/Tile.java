import java.util.*;

public class Tile {
    Block containingBlock;
    Board board;

    public Tile(Board board) {
        this.board = board;
        //
    }

    public List<Tile> getNeighbors() {
        return null;
    }

    public BoardPosition getPosition() {
        return board.getBoardPosition(this);
    }
}
