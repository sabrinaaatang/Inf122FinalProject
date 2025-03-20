package org.openjfx.inf122finalproject;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.net.URL;

/**
 * Abstract class representing a type of block in the game.
 * Blocks can be either image-based (e.g., Candy) or color-based (e.g., Tetris).
 */
public abstract class BlockType {
    private Image image;
    private String typeIdentifier;
    private Color color; // Used for color-based blocks like Tetris

    /**
     * Default constructor for subclasses that do not require initialization parameters.
     */
    public BlockType() {
    }

    /**
     * Constructor for image-based blocks, such as Candy.
     *
     * @param imagePath      The file path to the block's image
     * @param typeIdentifier A unique identifier for the block type
     */
    public BlockType(String imagePath, String typeIdentifier) {
        String resourcePath = imagePath.startsWith("/") ? imagePath : "/" + imagePath;

        URL imageUrl = getClass().getResource(resourcePath);
        if (imageUrl == null) {
            throw new IllegalArgumentException("Image not found at: " + resourcePath);
        }

        this.image = new Image(imageUrl.toExternalForm());
        this.typeIdentifier = typeIdentifier;
    }

    /**
     * Constructor for color-based blocks, such as Tetris-style blocks.
     *
     * @param color The color associated with the block
     */
    public BlockType(Color color) {
        this.color = color;
        this.image = null; // No image, only color
        this.typeIdentifier = null; // Not used for color-based blocks
    }

    /**
     * Gets the image associated with this block type.
     *
     * @return The block's image, or null if it is a color-based block
     */
    public Image getImage() {
        return image;
    }

    /**
     * Gets the unique identifier for this block type.
     *
     * @return The type identifier for image-based blocks, or null for color-based blocks
     */
    public String getTypeIdentifier() {
        return typeIdentifier;
    }

    /**
     * Gets the color associated with this block type.
     *
     * @return The block's color, or null if it is an image-based block
     */
    public Color getColor() {
        return color;
    }

    /**
     * Gets the shape representation of the block.
     *
     * @return A string representing the shape of the block
     */
    public abstract String getShape();

    /**
     * Gets the rotation states of the block.
     *
     * @return A 2D array representing the block's rotation states
     */
    public abstract int[][] getRotationStates();

    /**
     * Gets the effective width of the block.
     *
     * @return The width of the block
     */
    public abstract int getEffectiveWidth();

    /**
     * Gets the effective height of the block.
     *
     * @return The height of the block
     */
    public abstract int getEffectiveHeight();
}
