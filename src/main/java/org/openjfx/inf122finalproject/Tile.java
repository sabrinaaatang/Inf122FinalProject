package org.openjfx.inf122finalproject;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends StackPane {
    private Block containingBlock;
    private Rectangle border;  // Keeps the tile border
    private Rectangle colorFill; // Used for Tetris blocks
    private ImageView imageView;  // Used for Candy blocks
    private int tileSize;

    public Tile(int tileSize) {
        this.tileSize = tileSize;
        initialize();
    }

    private void initialize() {
        // Create a border for the tile
        border = new Rectangle(tileSize, tileSize);
        border.setFill(Color.TRANSPARENT);
        border.setStroke(Color.BLACK);

        // Create a colored rectangle (for Tetris blocks)
        colorFill = new Rectangle(tileSize, tileSize);
        colorFill.setFill(Color.TRANSPARENT);

        // Create an ImageView for candy images
        imageView = new ImageView();
        imageView.setFitWidth(tileSize);
        imageView.setFitHeight(tileSize);

        // StackPane layers in order: image (below), color (middle), border (top)
        this.getChildren().addAll(imageView, colorFill, border);
    }

    public void setContainingBlock(Block block) {
        this.containingBlock = block;
        updateAppearance();
    }

    public Block getContainingBlock() {
        return containingBlock;
    }

    private void updateAppearance() {
        if (containingBlock != null) {
            BlockType blockType = containingBlock.getBlockType();

            if (blockType.getImage() != null) { // If block has an image (Candy)
                imageView.setImage(blockType.getImage());
                imageView.setVisible(true);
                colorFill.setFill(Color.TRANSPARENT);
            } else { // If block uses color (Tetris)
                colorFill.setFill(blockType.getColor());
                imageView.setImage(null);
            }
        } else {
            imageView.setImage(null);
            colorFill.setFill(Color.TRANSPARENT);
        }
    }

}
