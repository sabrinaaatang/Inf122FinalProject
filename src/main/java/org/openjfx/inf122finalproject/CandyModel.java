package org.openjfx.inf122finalproject;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Pos;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Random;

public class CandyModel extends GameModel {
    /* Model Class
     * Function call to business logic and data access */
    private final CandyBoard candyBoard;
    private volatile ObjectProperty<Block[][]> blocks;     // create by the game manager?
//    private final CandyRule = new CandyGameRule();


    public CandyModel(int row, int col) {
        super(row, col);
        this.setBoard(new CandyBoard(row, col));
        candyBoard = (CandyBoard)this.getBoard();
        this.blocks = new SimpleObjectProperty<>(new Block[getRow()][getColumn()]);
        initBlocks();
    }

    public Tile[][] getGrid() { return candyBoard.getGrid(); }

    public ObjectProperty<Block[][]> candyBlocksProperty() { return this.blocks; }

    /* block manipulation */
    public void trySwap(Position p1, Position p2) {
        Block[][] blockBoard = blocks.get();
        ArrayList<Position> positions = new ArrayList<>();
        if(BlockSwap.performSwap(p1, p2, blocks)) {
            System.out.println("Swap success!");
            positions = checkMatchAfterSwap(blockBoard, p1, p2);

            if(!positions.isEmpty()) {
                final ArrayList<Position> pos = positions;
                Timeline timeline = new Timeline(
                    new KeyFrame(Duration.seconds(0.3), e -> BlockAnnihilation.perform(pos, this.blocks, this.candyBoard.getGrid())),
                    new KeyFrame(Duration.seconds(0.6), e -> autoDrop(this.blocks)),
                    new KeyFrame(Duration.seconds(0.9), e -> refillBoard(this.blocks))

                );


                timeline.setOnFinished(e -> {
                    final ArrayList<Position> matches = new ArrayList<>();
                    matches.add(new Position(-1, -1));  // Dummy entry to start the loop

                    Timeline timeline2 = new Timeline();
                    KeyFrame kf = new KeyFrame(Duration.seconds(0.5), ev -> {
                        if (!matches.isEmpty()) {  // Check condition
                            matches.clear();


                            Timeline stepTimeline = new Timeline(
                                    new KeyFrame(Duration.seconds(0.1), e1 -> CandyBoardChecker.findMatches(this.blocks, matches)),
                                    new KeyFrame(Duration.seconds(0.4), e1 -> BlockAnnihilation.perform(matches, this.blocks, this.candyBoard.getGrid())),
                                    new KeyFrame(Duration.seconds(0.7), e2 -> autoDrop(this.blocks)),
                                    new KeyFrame(Duration.seconds(1.0), e3 -> refillBoard(this.blocks))
                            );

                            stepTimeline.setOnFinished(e4 -> {
                                // Restart timeline2 to check for more matches
                                if (!matches.isEmpty()) {
                                    timeline2.playFromStart();
                                } else {
                                    timeline2.stop();  // Stop if no more matches
                                }
                            });

                            stepTimeline.play();
                        } else {
                            timeline2.stop();  // Stop if no matches found
                        }
                    });

                    timeline2.getKeyFrames().add(kf);
                    timeline2.setCycleCount(Animation.INDEFINITE);  // Keep checking until condition fails
                    timeline2.play(); // Start timeline
                });

                timeline.play();


            }
            else
                BlockSwap.performSwap(p1, p2, blocks);
        }
    }

    public ArrayList<Position> checkMatchAfterSwap(Block[][] blockBoard, Position p1, Position p2) {
        ArrayList<ArrayList<Position>> result = new ArrayList<>();
        if(p1.isAbove(p2))
            result = CandyBoardChecker.upDownChecker(blockBoard, p1, p2);
        else if(p1.isUnder(p2))
            result = CandyBoardChecker.upDownChecker(blockBoard, p2, p1);
        else if(p1.isBehind(p2))
            result = CandyBoardChecker.leftRightChecker(blockBoard, p1, p2);
        else if(p1.isAhead(p2))
            result = CandyBoardChecker.leftRightChecker(blockBoard, p2, p1);

        return CandyBoardChecker.flattener(result);
    }

    private void initBlocks() {
        Random rand = new Random();
        Block[][] candyBlocks= this.blocks.get();
        Tile[][] tiles = this.candyBoard.getGrid();
        final BlockType[] blockTypes = {BlockType.PINK_CANDY, BlockType.CHOCO_CANDY, BlockType.BROWN_CANDY,
                BlockType.YELLOW_CANDY, BlockType.ICECREAM_CANDY, BlockType.REAL_CANDY, BlockType.CREAM_CANDY};
        for(int i = 0; i < getRow(); i++) {
            for(int j = 0; j < getColumn(); j++) {
                Block candyBlock = BlockFactory.createBlock(blockTypes[rand.nextInt(blockTypes.length)]);
                candyBlock.addTile(tiles[i][j]);
                tiles[i][j].setContainsEmpty(false);
                candyBlocks[i][j] = candyBlock;
            }
        }
        this.candyBlocksProperty().set(candyBlocks);
    }

    private synchronized void autoDrop(ObjectProperty<Block[][]> blockBoard) {
        Tile[][] tiles = this.candyBoard.getGrid();
        final int row = tiles.length;  // 0 -> row-1
        final int col = tiles[0].length;    //0->col-1
        Block[][] bs = blockBoard.get();

        for(int r = row - 2; r >= 0; r--)   // the last row will not drop in any cases
        {
            for (int c = 0; c < col; c++) {
                Block candyBlock = bs[r][c];
//                System.out.println(candyBlock );
                candyBlock.autoDropBehavior.autoDrop(blockBoard, tiles, new Position(c, r));
            }
        }
    }

    private synchronized void refillBoard(ObjectProperty<Block[][]> blockBoard) {
        Tile[][] tiles = this.candyBoard.getGrid();
        final int row = tiles.length;
        final int col = tiles[0].length;
        Block[][] receiver = blockBoard.get();
        Block[][] donor = CandyBoard.randomCandyBlock(row, col);
        for(int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                Block b = receiver[r][c];
                Tile t = tiles[r][c];
                if(b.isEmptyType())
                {
                    receiver[r][c] = donor[r][c];
                    t.setContainsEmpty(false);
                    receiver[r][c].addTile(t);
                }
            }
        }
        blockBoard.set(null);
        blockBoard.set(receiver);
    }

    private static void print(ArrayList<Position> matches) {
        System.out.println("PRINT MATCHES HERE: ");
        for(Position p : matches) {
            System.out.println(p + " | ");
        }
    }



    public Block getBlock(Position p) {
        return this.blocks.get()[p.getRow()][p.getColumn()];
    }

}
