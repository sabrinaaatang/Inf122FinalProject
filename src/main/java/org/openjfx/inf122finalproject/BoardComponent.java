package org.openjfx.inf122finalproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

import java.io.IOException;

/* a board can be used to display other info, e.g. next block? extend this class to make scoreboard? */
public class BoardComponent extends AnchorPane {

    @FXML
    private AnchorPane ap;

    @FXML
    public void initialize() {
        renderBoard();
    }

    /* IMPORTANT. In order to make the component reusable. Class should extend the root container: e.g. GridPane.
     * Create constructor for this. Now this component <CandyCrushView> can be reused inside other fxml file. */
    public BoardComponent() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("board-view.fxml"));
        fxmlLoader.setRoot(this);   /* must set root */

        /* set its controller to itself -> initialize() function will be called.
         *  set controller here to avoid infinite recursion. If set controller in
         *  fxml file to this file will cause infinite recursion */
        fxmlLoader.setController(BoardComponent.this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    public void renderBoard() {
        /* sample code copy from document */
        Circle circle = new Circle();
        circle.setFill(Color.GREEN );
        circle.setRadius(10);

        Label connLabel = new Label("Connection");

        HBox connHBox = new HBox();
        connHBox.setSpacing( 4.0d );
        connHBox.setAlignment(Pos.BOTTOM_RIGHT);
        connHBox.getChildren().addAll( connLabel, circle );

        AnchorPane.setBottomAnchor( connHBox, 10.0d );
        AnchorPane.setRightAnchor( connHBox, 10.0d );

        ap.getChildren().add( connHBox );
    }

}
