import java.awt.Point;
import java.util.List;
import java.util.ArrayList;

public class BlockRotate implements BlockManipulator {
    private Board board;
    private Block block;

    public BlockRotate(Board board, Block block) {
        this.board = board;
        this.block = block;
    }

    @Override
    public void manipulate() {
        System.out.println("initiated block swap");

        Point center = block.centreOfMass;
        List<Tile> tiles = block.tiles;
        List<BoardPosition> newPositions = new ArrayList<>();

        // get new positions after rotation
        for (Tile tile : tiles) {
            BoardPosition pos = board.getBoardPosition(tile);
            if (pos == null) return; // if tile is missing, exit

            int relativeX = pos.x - center.x;
            int relativeY = pos.y - center.y;

            // 90-degree counterclockwise rotation TODO: is this correct rotation?
            int newX = center.x - relativeY;
            int newY = center.y + relativeX;

            if (newX < 0 || newX >= board.width || newY < 0 || newY >= board.height) {
                System.out.println("invalid rotation at: (" + newX + ", " + newY + ")");
                return; // blocked rotation
            }

            newPositions.add(board.grid[newY][newX]);
        }

        // apply rotation
        for (int i = 0; i < tiles.size(); i++) {
            newPositions.get(i).tile = tiles.get(i);
        }

        board.updateBoard();
    }
}
