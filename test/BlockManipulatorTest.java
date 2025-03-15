import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

public class BlockManipulatorTest {
    private Board board;
    private TestBlock block1, block2;

    @BeforeEach
    public void setUp() {
        board = new Board(10, 10);
        
        // create test blocks with predefined positions
        block1 = TestBlock.createTestBlock(board, new int[][]{{2, 2}, {3, 2}});
        block2 = TestBlock.createTestBlock(board, new int[][]{{4, 4}, {5, 4}});

        board.blocks.add(block1);
        board.blocks.add(block2);
    }

    @Test
    public void testBlockSwap() {
        BlockManipulator swap = new BlockSwap(board, block1, block2);
        swap.manipulate();

        BoardPosition pos1 = board.getBoardPosition(block1.tiles.get(0));
        BoardPosition pos2 = board.getBoardPosition(block2.tiles.get(0));

        assertNotNull(pos1);
        assertNotNull(pos2);
        assertEquals(4, pos1.x);
        assertEquals(4, pos1.y);
        assertEquals(2, pos2.x);
        assertEquals(2, pos2.y);
    }

    @Test
    public void testBlockRotate() {
        BlockManipulator rotate = new BlockRotate(board, block1);
        rotate.manipulate();

        List<Tile> tiles = block1.tiles;
        BoardPosition pos1 = board.getBoardPosition(tiles.get(0));
        BoardPosition pos2 = board.getBoardPosition(tiles.get(1));

        assertNotNull(pos1);
        assertNotNull(pos2);
        assertTrue(pos1.x != 2 || pos1.y != 2); // ensure tile has moved
    }

    @Test
    public void testBlockMove() {
        BlockManipulator moveRight = new BlockMove(board, block1, 1, 0);
        moveRight.manipulate();

        BoardPosition pos1 = board.getBoardPosition(block1.tiles.get(0));
        BoardPosition pos2 = board.getBoardPosition(block1.tiles.get(1));

        assertNotNull(pos1);
        assertNotNull(pos2);
        assertEquals(3, pos1.x);
        assertEquals(2, pos1.y);
        assertEquals(4, pos2.x);
        assertEquals(2, pos2.y);
    }
}
