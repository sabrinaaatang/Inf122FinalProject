import java.util.List;

public class BlockSwap implements BlockManipulator {
    private Board board;
    private Block block1, block2;

    public BlockSwap(Board board, Block block1, Block block2) {
        this.board = board;
        this.block1 = block1;
        this.block2 = block2;
    }

    @Override
    public void manipulate() {
        System.out.println("initiated block swap");

        List<Tile> tiles1 = block1.tiles;
        List<Tile> tiles2 = block2.tiles;

        if (tiles1.size() != tiles2.size()) {
            System.out.println("cannot swap blocks of different sizes.");
            return;
        }

        for (int i = 0; i < tiles1.size(); i++) {
            Tile tileA = tiles1.get(i);
            Tile tileB = tiles2.get(i);

            BoardPosition posA = board.getBoardPosition(tileA);
            BoardPosition posB = board.getBoardPosition(tileB);

            if (posA == null || posB == null) continue;

            // swap tiles in board positions
            posA.tile = tileB;
            posB.tile = tileA;
        }
        board.updateBoard();
    }
}
