package org.openjfx.inf122finalproject;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Random;
import java.util.TreeMap;

public class CandyCrushView extends GridPane {
    private Random rand = new Random();
    public Color[] colors = {Color.RED, Color.AZURE, Color.BLUE, Color.GOLD, Color.PURPLE};
    private final VBox[][] blocks = new VBox[UIConfig.numOfRowCandy][UIConfig.numOfColCandy];
    private VBox selectedBox = null;
//    private final TreeMap<Tile, VBox> blocks;

    @FXML
    private GridPane grid;

    @FXML public void initialize() {
        renderGrid();
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


    public void renderGrid() {
        int row_pos = 0;
        for (int row = UIConfig.startRow; row < UIConfig.startRow + UIConfig.numOfRowCandy; row++)
        {
            for (int col = 0; col < UIConfig.numOfColCandy; col++) {
                /* Create a vertical box VBox that contains a Rectangle inside */
                VBox vbox = new VBox(new Rectangle(UIConfig.block_width, UIConfig.block_height, colors[rand.nextInt(5)]));
                vbox.setId("candy_block");
                vbox.getStyleClass().add("block-vbox");
//                vbox.setUserData();
                vbox.setPickOnBounds(true);         //want to detect clicks even when clicking child nodes
                vbox.setOnMouseClicked(event -> {
                        VBox clickedCandy = (VBox) event.getSource();
                        System.out.println("Clicked Candy at Row: " + (GridPane.getRowIndex(clickedCandy) - UIConfig.startRow) +
                                ", Col: " + GridPane.getColumnIndex(clickedCandy));
                        onClickSelection(clickedCandy);
                });
                vbox.setStyle("-fx-border-width: 0px 0px 0px 0px; ");
                blocks[row_pos][col] = vbox;
                grid.add(vbox, col, row);
            }
            ++row_pos;
        }
    }


    public void onClickSelection(VBox vbox) {
        if(selectedBox == vbox || vbox == null)  return;

        if (selectedBox != null) {
            clearBorder(selectedBox);
        }

        setBorder(vbox);
        selectedBox = vbox;
    }


    public void clearBorder(VBox vbox) {
        vbox.setStyle("-fx-border-width: 0px;");
    }

    public void setBorder(VBox vbox) {
        vbox.setStyle("-fx-border-width: 2px; -fx-border-insets: -2px; -fx-border-color: #3b3bf3 ");
    }
}
