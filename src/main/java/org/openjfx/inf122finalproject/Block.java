package org.openjfx.inf122finalproject;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.awt.Point;
import java.util.Objects;

public abstract class Block {
    ArrayList<Tile> tiles;
    boolean isEmpty = false;
    BlockType type;
    int score = 0;
//    Tile centreOfMass;
    BlockManipulatorContext bm = new BlockManipulatorContext();
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

    public void setType(int score) {
        this.score = score;
    }

    public void setImageView(String path) {
        Image img = new Image(Objects.requireNonNull(getClass()
                .getResourceAsStream(path)));

        this.img = new ImageView(img);
        this.img.setFitHeight(UIConfig.block_height);
        this.img.setFitWidth(UIConfig.block_width);
    }
    public void setImageView(ImageView img) {
        this.img = img;
        this.img.setFitHeight(UIConfig.block_height);
        this.img.setFitWidth(UIConfig.block_width);
    }

    public ImageView getImageView() {
        if (img == null) {
            System.out.println("image view is null");
        }
        return img;
    }

    @Override
    public String toString() {
        return "A " + this.type + " Block";
    }
}
