package org.openjfx.inf122finalproject;

public class CandyBlock extends Block {
    public static final String IMG_PATH = "/org/openjfx/inf122finalproject/candy/";


    public CandyBlock(BlockType type) {
        super(type);
        this.autoDropBehavior = new AutoDrop();
        switch(this.type) {
            case PINK_CANDY -> this.setImageView(IMG_PATH + "pink_candy.png");
            case CHOCO_CANDY -> this.setImageView(IMG_PATH + "choco_candy.png");
            case BROWN_CANDY -> this.setImageView(IMG_PATH + "brown_candy.png");
            case YELLOW_CANDY -> this.setImageView(IMG_PATH + "yellow_candy.png");
        }
    }



    @Override
    public String toString() {
        return "A " + this.type + " Candy Block";
    }


}
