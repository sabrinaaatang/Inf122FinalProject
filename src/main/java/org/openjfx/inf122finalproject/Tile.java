package org.openjfx.inf122finalproject;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.animation.FadeTransition;
import javafx.util.Duration;


import java.util.List;

public class Tile extends StackPane {
    Block containingBlock;
    private Rectangle rectangle;
    int tileSize;

    public Tile(int tileSize) {
        this.tileSize = tileSize;
        initialize();
    }

    private void initialize() {
        rectangle = new Rectangle(tileSize, tileSize);
        rectangle.setFill(Color.TRANSPARENT);
        rectangle.setStroke(Color.BLACK);
        this.getChildren().add(rectangle);
    }

    public List<Tile> getNeighbors() {
        return null;
    }

    public void setContainingBlock(Block block) {
        Color currentColor = Color.TRANSPARENT;
        Color newColor = Color.TRANSPARENT;
        if(this.containingBlock != null){
            currentColor = this.containingBlock.getBlockType().getColor();
        }
        if(block != null){
            newColor =  block.getBlockType().getColor();
        }
        this.containingBlock = block;
        updateColor(currentColor, newColor);
    }

    public Block getContainingBlock() {
        return containingBlock;
    }

    public void updateColor(Color currentColor, Color newColor) {
        if (newColor != Color.TRANSPARENT) {
            rectangle.setFill(newColor);
            //rectangle.setOpacity(1.0);
        } else if (currentColor != Color.TRANSPARENT) {
            rectangle.setFill(Color.TRANSPARENT);
          /*  if (!rectangle.getFill().equals(Color.TRANSPARENT)) {
                FadeTransition fade = new FadeTransition(Duration.millis(500), rectangle);
                fade.setFromValue(1.0);
                fade.setToValue(0.0);
                fade.setOnFinished(event -> {
                    rectangle.setFill(Color.TRANSPARENT);
                    rectangle.setOpacity(1.0); // Reset for future animations.
                });
                fade.play();
            } else {
                rectangle.setFill(Color.TRANSPARENT);
            }*/
        }
    }
}
