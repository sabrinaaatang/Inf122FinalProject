package org.openjfx.inf122finalproject;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class CandyBoard extends Board {

    public CandyBoard(int height, int width) {
        super(height, width);
        addCandyTileSetting();
    }

    public void addCandyTileSetting() {
        for (int row = 0; row < this.getHeight(); row++) {
            for (int col = 0; col < this.getWidth(); col++) {
                Tile tile = this.getGrid()[row][col];
                tile.setId("candy_block");
                tile.getStyleClass().add("block-vbox");
                tile.setStyle("-fx-border-width: 0;");
//                tile.setUserData();
                tile.setPickOnBounds(true);
            }
        }
    }

}
