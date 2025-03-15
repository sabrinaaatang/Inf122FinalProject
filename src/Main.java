public class Main {
    public static void main(String[] args) {
        Board board = new Board(10, 10);
        Block block1 = new TestBlock("Block1");
        Block block2 = new TestBlock("Block2");

        // place blocks on the board
        board.placeTile(new Tile(board), 2, 2);
        board.placeTile(new Tile(board), 3, 2);
        board.placeTile(new Tile(board), 4, 4);
        board.placeTile(new Tile(board), 5, 4);

        BlockManipulatorContext context = new BlockManipulatorContext();
        
        context.setAlgorithm(new BlockSwap(board, block1, block2));
        context.performManipulation(); // swaps two blocks

        context.setAlgorithm(new BlockRotate(board, block1));
        context.performManipulation(); // rotates a block

        context.setAlgorithm(new BlockMove(board, block1, 1, 0)); // move right
        context.performManipulation();
    }
}
