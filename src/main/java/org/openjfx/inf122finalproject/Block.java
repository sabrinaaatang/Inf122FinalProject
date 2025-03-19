package org.openjfx.inf122finalproject;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.awt.Point;
import java.util.Objects;

public abstract class Block {
    public static final String IMG_PATH = "/org/openjfx/inf122finalproject/candy/";

    private final ArrayList<Tile> tiles;
    private boolean isEmpty = false;
    private BlockType type;
    private int score = 100;
//    Tile centreOfMass;
    private ImageView img;
    AutoDropBehavior autoDropBehavior;

    public Block(BlockType type) {
        this.type = type;
        tiles = new ArrayList<>();
    }

    public void placeBlock(Board board) {
        //
    }

    public void removeBlock(Board board) {
        //
    }

    // more functions
    public ArrayList<Tile> getTiles() {
        return tiles;
    }

    public void setIsEmpty(boolean empty) {
        isEmpty = empty;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public boolean isEmptyType() {
        return type == BlockType.EMPTY_BLOCK;
    }

    public void setType(BlockType type) {
        this.type = type;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setImageView(String path , int size) {
        Image img = new Image(Objects.requireNonNull(getClass()
                .getResourceAsStream(path)));

        this.img = new ImageView(img);
        this.img.setFitHeight(size);
        this.img.setFitWidth(size);
    }
    public void setImageView(ImageView img, int size) {
        this.img = img;
        this.img.setFitHeight(size);
        this.img.setFitWidth(size);
    }
    public void setImageSize(double width, double height) {
        this.img.setFitWidth(width);
        this.img.setFitHeight(height);
    }

    public ImageView getImageView() {
        if (img == null) {
            System.out.println("image view is null");
        }
        return img;
    }

    public void addTile(Tile tile) {
        this.tiles.add(tile);
    }

    public void removeTile(Tile tile) {
        this.tiles.remove(tile);
    }

    public void clearAllTiles() {
        tiles.clear();
    }

    public BlockType getType() {
        return type;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "A " + this.type + " Block";
    }
}
