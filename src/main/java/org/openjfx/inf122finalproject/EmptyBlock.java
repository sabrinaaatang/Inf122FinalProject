package org.openjfx.inf122finalproject;


public class EmptyBlock extends Block {

    public EmptyBlock(BlockType type) {
        super(type);
        this.setIsEmpty(true);
        this.autoDropBehavior = new NoAutoDrop();
        this.setImageView(IMG_PATH + "empty_block.png", UIConfig.candySize);
    }

    @Override
    public String toString() {
        return "A " + this.getType() + " Tetris Block";
    }

}
