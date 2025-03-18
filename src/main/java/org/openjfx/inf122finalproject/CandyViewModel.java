package org.openjfx.inf122finalproject;

import javafx.beans.property.*;
import javafx.scene.layout.VBox;


public class CandyViewModel {
    /* ViewModel Class:
     * Define event handler in VW(viewModel) class */
    private final CandyModel candyBoard ;
    private final int row = UIConfig.numOfRowCandy;
    private final int col = UIConfig.numOfColCandy;

    private final ObjectProperty<Tile> prevSelect = new SimpleObjectProperty<>(null);

    /**
     * Init this class will also init the Model class.
     */
    public CandyViewModel() {
        candyBoard = new CandyModel(UIConfig.numOfRowCandy, UIConfig.numOfColCandy);
    }

    public ObjectProperty<Block[][]> candyBlocksProperty() { return candyBoard.candyBlocksProperty(); }

    public ObjectProperty<Tile> prevSelectProperty() { return prevSelect; }

    public void setPrevSelect(Tile currSelect) {
        this.prevSelect.set(currSelect);
    }

    public void onClickSelection(Position pos, Tile currSelect) {
        if (this.prevSelect.get() == currSelect || currSelect == null) return;

        if(prevSelect.get() != null && isNextTo(this.prevSelect.get(), currSelect)) {
            candyBoard.trySwap(this.prevSelect.get().getPosition(), currSelect.getPosition());
            clearBorder(prevSelect.get());
            prevSelect.set(null);
        }
        else {
            setPrevSelect(currSelect);
        }
    }

    public Tile[][] getCandyGrid() {
        return candyBoard.getGrid();
    }


    public void clearBorder(Tile tile) {
        if(tile == null) return;
        tile.setStyle("-fx-border-width: 0px;");
    }

    public void setBorder(Tile tile) {
        if(tile == null) return;
        tile.setStyle("-fx-border-width: 2px; -fx-border-insets: -2px; -fx-border-color: #3b3bf3 ");
    }

    private boolean isNextTo(Tile prev, Tile curr) {
        return prev.getPosition().isNextTo(curr.getPosition(), this.row, this.col);
    }
}
