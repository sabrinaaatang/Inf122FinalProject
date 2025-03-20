package org.openjfx.inf122finalproject;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Represents a tile on the game board.
 * A tile can either be empty or contain a block (Candy or Tetris).
 * It visually updates based on the block it contains.
 */
public class Tile extends StackPane {
    private Block containingBlock;
    private Rectangle border;  // Tile border
    private Rectangle colorFill; // Used for Tetris blocks
    private ImageView imageView;  // Used for Candy blocks
    private int tileSize;

    /**
     * Constructs a Tile with a specified size.
     *
     * @param tileSize The size of the tile in pixels
     */
    public Tile(int tileSize) {
        this.tileSize = tileSize;
        initialize();
    }

    /**
     * Initializes the tile's appearance with a border, color fill, and image view.
     */
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

        // StackPane layers in order: image (bottom), color (middle), border (top)
        this.getChildren().addAll(imageView, colorFill, border);
    }

    /**
     * Sets the block that this tile contains and updates its appearance accordingly.
     *
     * @param block The block to set in this tile, or null to clear the tile
     */
    public void setContainingBlock(Block block) {
        this.containingBlock = block;
        updateAppearance();
    }

    /**
     * Gets the block contained within this tile.
     *
     * @return The block inside this tile, or null if empty
     */
    public Block getContainingBlock() {
        return containingBlock;
    }

    /**
     * Updates the tile's appearance based on the block it contains.
     * If the block has an image (Candy), it sets the image view.
     * If the block is a Tetris block, it sets a colored fill.
     */
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
