package org.openjfx.inf122finalproject;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.TreeMap;

public class CandyCrushView extends GridPane {
    private Tile[][] gridContainer = null;
    private final CandyViewModel viewModel;

    @FXML
    private GridPane gridPane;

    /* bind property & add listener here */
    @FXML public void initialize() {
        gridContainer = viewModel.getCandyGrid();

        bindProperty();
        setListener();
        initGrid();
    }

    /* IMPORTANT. In order to make the component reusable. Class should extend the root container: e.g. GridPane.
     * Create constructor for this. Now this component <CandyCrushView> can be reused inside other fxml file. */
    public CandyCrushView() {
        viewModel = new CandyViewModel();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("candy-crush-view.fxml"));
        fxmlLoader.setRoot(this);

        /* set its controller to itself -> initialize() function will be called.
        *  set controller here to avoid infinite recursion. If set controller in
        *  fxml file to this file will cause infinite recursion */
        fxmlLoader.setController(CandyCrushView.this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void initGrid() {
        Block[][] grids = this.viewModel.candyBlocksProperty().get();
        gridPane.getChildren().clear();

        for (int r = 0; r < UIConfig.numOfRowCandy; r++) {
            for (int c = 0; c < UIConfig.numOfColCandy; c++) {
                Tile tile = this.gridContainer[r][c];
                tile.getChildren().clear();
                tile.getChildren().add(grids[r][c].getImageView());
                tile.setOnMouseClicked(event -> {
                    Tile clickedCandy = (Tile) event.getSource();
                    viewModel.onClickSelection(clickedCandy.getPosition(), clickedCandy);
                });
                gridPane.add(tile, c, r + UIConfig.startRow);
            }
        }
    }


    public void updateGrid(Block[][] newGrid) {
        assert(gridContainer != null);

        /* Don't make change when newGrid is null because the mechanism behind
           property triggered when the reference inside the property changes */
        if(newGrid == null) { return; }

        System.out.println("Board Property triggered.");

        gridPane.getChildren().clear();
        for (int r = 0; r < UIConfig.numOfRowCandy; r++) {
            for (int c = 0; c < UIConfig.numOfColCandy; c++) {
                Tile tile = this.gridContainer[r][c];
                tile.getChildren().clear();
                tile.getChildren().add(newGrid[r][c].getImageView());
                gridPane.add(tile, c, r + UIConfig.startRow);
            }
        }
    }

    public void bindProperty() {

    }

    public void setListener() {
        /* function call when property value changed */
        viewModel.prevSelectProperty().addListener((observable, oldTile, newTile) -> {
            viewModel.clearBorder(oldTile);
            viewModel.setBorder(newTile);
        });

        viewModel.candyBlocksProperty().addListener((observable, oldGrid, newGrid) -> updateGrid(newGrid));

    }

    public static void main(String[] args) {
        System.out.println("Current Working Directory: " + System.getProperty("user.dir"));
    }
}
