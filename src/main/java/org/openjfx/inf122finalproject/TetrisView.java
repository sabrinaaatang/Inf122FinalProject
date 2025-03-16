package org.openjfx.inf122finalproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.util.Random;
/*  App
Main entry point

View
UI controls

Model
Function call to business logic and data access

ViewModel
Contains screen state and UI logic

Domain object
UI-neutral transfer object

Converter
Helper class for ViewModel to Model communication*/

/* View class: define UI and bound them with event handler defined in VM */
public class TetrisView extends GridPane {
    /* maybe exist a better structure for this */
    private final VBox[][] blocks = new Tile[UIConfig.numOfRowTetris][UIConfig.numOfColTetris];

    @FXML
    private GridPane grid;

    @FXML
    public void initialize() {
        renderGrid();
    }

    /* IMPORTANT. In order to make the component reusable. Class should extend the root container: e.g. GridPane.
     * Create constructor for this. Now this component <CandyCrushView> can be reused inside other fxml file. */
    public TetrisView() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("tetris-view.fxml"));

        fxmlLoader.setRoot(this);   /* must set root */

        /* set its controller to itself -> initialize() function will be called.
         *  set controller here to avoid infinite recursion. If set controller in
         *  fxml file to this file will cause infinite recursion */
        fxmlLoader.setController(TetrisView.this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /* just init a gridpane for debug purpose */
    public void renderGrid() {
        int row_pos = 0;
        for (int row = UIConfig.startRow; row < UIConfig.startRow + UIConfig.numOfRowTetris; row++) {
            for (int col = 0; col < UIConfig.numOfColTetris; col++) {

                /* Create a Tile (extends vbox) box that contains a Rectangle inside */
                VBox tile = new Tile(new Rectangle(UIConfig.tetrisBlockWidth,
                        UIConfig.tetrisBlockHeight, Color.rgb(83,83,83)), row_pos, col);
                tile.setId("tetris_block");
                tile.getStyleClass().add("tetris-block-vbox");
//                tile.setUserData(some data here);
//                tile.setStyle("-fx-border-width: 0px;");
                blocks[row_pos][col] = tile;
                grid.add(tile, col, row);
            }
            ++row_pos;
        }
    }
}

