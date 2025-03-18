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
    private Random rand = new Random();
    private Tile[][] gridContainer = null;
    private final CandyViewModel viewModel = new CandyViewModel();

    @FXML
    private GridPane gridPane;

    /* bind property & add listener here */
    @FXML public void initialize() {
        gridContainer = viewModel.getCandyGrid();
        renderGrid();
        bindProperty();
        setListener();
    }

    /* IMPORTANT. In order to make the component reusable. Class should extend the root container: e.g. GridPane.
     * Create constructor for this. Now this component <CandyCrushView> can be reused inside other fxml file. */
    public CandyCrushView() {
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


//    CandyBlock[][] blocks
    public void renderGrid() {
        String fp = "/org/openjfx/inf122finalproject/candy/candy";
        assert(gridContainer != null);

        gridPane.getChildren().clear();
        for (int r = 0; r < UIConfig.numOfRowCandy; r++) {
            for (int c = 0; c < UIConfig.numOfColCandy; c++) {
                Tile tile = this.gridContainer[r][c];
                tile.getChildren().clear();
                int randInt = rand.nextInt(4);
                Image img = new Image(Objects.requireNonNull(getClass()
                .getResourceAsStream( fp + (randInt+1) + ".png" )));

                ImageView candy = new ImageView(img);
//
                candy.setFitHeight(UIConfig.block_height);
                candy.setFitWidth(UIConfig.block_width);
                tile.getChildren().add(candy);
                tile.setOnMouseClicked(event -> {
                    Tile clickedCandy = (Tile) event.getSource();
                    System.out.println("Clicked Candy at Row: " + (GridPane.getRowIndex(clickedCandy) - UIConfig.startRow) +
                            ", Col: " + GridPane.getColumnIndex(clickedCandy));
                    viewModel.onClickSelection(clickedCandy);
                });
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

//        viewModel.candyBlocksProperty().addListener((observable, oldGrid, newGrid) -> renderGrid(newGrid));

    }

    public static void main(String[] args) {
        System.out.println("Current Working Directory: " + System.getProperty("user.dir"));
    }
}
