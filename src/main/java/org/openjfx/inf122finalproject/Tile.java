package org.openjfx.inf122finalproject;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

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
        this.containingBlock = block;
        updateColor();
    }

    public Block getContainingBlock() {
        return containingBlock;
    }

    public void updateColor() {
        if (containingBlock != null) {
            rectangle.setFill(containingBlock.getBlockType().getColor());
        } else {
            rectangle.setFill(Color.TRANSPARENT);
        }
    }
}
