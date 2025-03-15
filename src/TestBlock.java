import java.awt.Point;

public class TestBlock extends Block {
    public TestBlock(String name) {
        super(name);
    }

    // create a block with predefined tiles for testing
    public static TestBlock createTestBlock(Board board, int[][] positions) {
        TestBlock block = new TestBlock("TestBlock");

        for (int[] pos : positions) {
            int x = pos[0];
            int y = pos[1];
            Tile tile = new Tile(board);
            block.tiles.add(tile);
            board.placeTile(tile, x, y); // assign tile to board position
        }

        //set the center of mass as the first tile (for simplicity)
        if (!block.tiles.isEmpty()) {
            BoardPosition centerPos = board.getBoardPosition(block.tiles.get(0));
            if (centerPos != null) {
                block.centreOfMass = new Point(centerPos.x, centerPos.y);
            }
        }

        return block;
    }
}
