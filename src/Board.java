import java.util.*;

public class Board {
    int height;
    int width;
    BoardPosition[][] grid;
    ArrayList<Block> blocks;

    public Board(int height, int width) {
        this.height = height;
        this.width = width;
    }

    public void initialize(int height, int width) {
        // i feel like this method might be unnecessary since the constructor will also be setting the height and width
    }

    public void updateBoard() {
        //
    }

    public void applyMove(TileManipulatorContext action) {
        //
    }
    
}