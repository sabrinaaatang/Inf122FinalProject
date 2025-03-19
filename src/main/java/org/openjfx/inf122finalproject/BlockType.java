package org.openjfx.inf122finalproject;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.net.URL;

public abstract class BlockType {
    private Image image;
    private String typeIdentifier;
    private Color color; // Used for Tetris blocks

    // Constructor for Candy (image-based blocks)

    public BlockType(){

    }
    public BlockType(String imagePath, String typeIdentifier) {
        String resourcePath = imagePath.startsWith("/") ? imagePath : "/" + imagePath;

        URL imageUrl = getClass().getResource(resourcePath);
        if (imageUrl == null) {
            throw new IllegalArgumentException("Image not found at: " + resourcePath);
        }

        this.image = new Image(imageUrl.toExternalForm());
        this.typeIdentifier = typeIdentifier;
    }

    // Constructor for Tetris (color-based blocks)
    public BlockType(Color color) {
        this.color = color;
        this.image = null; // No image, only color
        this.typeIdentifier = null; // Not used for Tetris
    }

    public Image getImage() {
        return image;
    }

    public String getTypeIdentifier() {
        return typeIdentifier;
    }

    public Color getColor() {
        return color; // Returns color for Tetris blocks, null for Candy blocks
    }

    public abstract String getShape();
    public abstract int[][] getRotationStates();
    public abstract int getEffectiveWidth();
    public abstract int getEffectiveHeight();
}
