import java.util.List;
import java.util.ArrayList;

public class BlockMove implements BlockManipulator {
    private Board board;
    private Block block;
    private int dx, dy; // movement direction

    public BlockMove(Board board, Block block, int dx, int dy) {
        this.board = board;
        this.block = block;
        this.dx = dx;
        this.dy = dy;
    }

    @Override
    public void manipulate() {
        System.out.println("initiated block swap");

        List<Tile> tiles = block.tiles;
        List<BoardPosition> newPositions = new ArrayList<>();

        // get new positions
        for (Tile tile : tiles) {
            BoardPosition pos = board.getBoardPosition(tile);
            if (pos == null) return;

            int newX = pos.x + dx;
            int newY = pos.y + dy;

            if (newX < 0 || newX >= board.width || newY < 0 || newY >= board.height) {
                System.out.println("invalid move at: (" + newX + ", " + newY + ")");
                return; // blocked movement
            }

            newPositions.add(board.grid[newY][newX]);
        }

        // apply movement
        for (int i = 0; i < tiles.size(); i++) {
            newPositions.get(i).tile = tiles.get(i);
        }

        board.updateBoard();
    }
}
