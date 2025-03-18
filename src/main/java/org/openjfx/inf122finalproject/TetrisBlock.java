package org.openjfx.inf122finalproject;

public class TetrisBlock extends Block {
    public TetrisBlock(BlockType type) {
        super(type);
    }

    @Override
    public String toString() {
        return "A " + this.getType() + " Tetris Block";
    }


}
