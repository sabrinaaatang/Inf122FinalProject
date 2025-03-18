package org.openjfx.inf122finalproject;

import java.util.Random;

public class CandyBlock extends Block {
    private final static Random rand = new Random();

    private boolean isMatchable = true;
    private static BlockType[] candyTypes = new BlockType[]{
        BlockType.YELLOW_CANDY, BlockType.PINK_CANDY, BlockType.BROWN_CANDY, BlockType.CHOCO_CANDY,
            BlockType.CREAM_CANDY, BlockType.ICECREAM_CANDY, BlockType.REAL_CANDY
    };

    public CandyBlock(BlockType type) {
        super(type);
        this.autoDropBehavior = new AutoDrop();
        switch(type) {
            case PINK_CANDY -> this.setImageView(IMG_PATH + "pink_candy.png", UIConfig.candySize);
            case CHOCO_CANDY -> this.setImageView(IMG_PATH + "choco_candy.png", UIConfig.candySize);
            case BROWN_CANDY -> this.setImageView(IMG_PATH + "brown_candy.png", UIConfig.candySize);
            case YELLOW_CANDY -> this.setImageView(IMG_PATH + "yellow_candy.png", UIConfig.candySize);
            case ICECREAM_CANDY -> this.setImageView(IMG_PATH + "icecream_candy.png", UIConfig.candySize);
            case CREAM_CANDY -> this.setImageView(IMG_PATH + "cream_candy.png", UIConfig.candySize);
            case REAL_CANDY -> this.setImageView(IMG_PATH + "real_candy.png", UIConfig.candySize);
        }
    }

    public boolean isMatchable() {
        return isMatchable;
    }

    public void toRandomCandy() {
        BlockType type = candyTypes[rand.nextInt(candyTypes.length)];
        this.setType(type);
        switch(type) {
            case PINK_CANDY -> this.setImageView(IMG_PATH + "pink_candy.png", UIConfig.candySize);
            case CHOCO_CANDY -> this.setImageView(IMG_PATH + "choco_candy.png", UIConfig.candySize);
            case BROWN_CANDY -> this.setImageView(IMG_PATH + "brown_candy.png", UIConfig.candySize);
            case YELLOW_CANDY -> this.setImageView(IMG_PATH + "yellow_candy.png", UIConfig.candySize);
            case ICECREAM_CANDY -> this.setImageView(IMG_PATH + "icecream_candy.png", UIConfig.candySize);
            case CREAM_CANDY -> this.setImageView(IMG_PATH + "cream_candy.png", UIConfig.candySize);
            case REAL_CANDY -> this.setImageView(IMG_PATH + "real_candy.png", UIConfig.candySize);
        }
    }

    public void setMatchable(boolean matchable) {
        isMatchable = matchable;
    }



    @Override
    public String toString() {
        return "A " + this.getType() + " Candy Block";
    }


}
