package org.openjfx.inf122finalproject;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class CandyModel extends GameModel {
    /* Model Class
     * Function call to business logic and data access */
    private int row ;
    private int col ;
    private final CandyBoard candyBoard;
    private ObjectProperty<CandyBlock[][]> blocks = null;     // create by the game manager?
//    private final CandyRule = new CandyGameRule();

    public CandyModel(int row, int col) {
        super(row, col);
        this.setBoard(new CandyBoard(row, col));
        candyBoard = (CandyBoard)this.getBoard();
        initBlocks();
    }

    public Tile[][] getGrid() { return candyBoard.getGrid(); }

    public ObjectProperty<CandyBlock[][]> candyBlocksProperty() { return this.blocks; }

    /* block manipulation */
    public void trySwap(Tile t1, Tile t2) {
        System.out.println("Modify Model class' Data");
    }

    public void initBlocks() {

    }



}
