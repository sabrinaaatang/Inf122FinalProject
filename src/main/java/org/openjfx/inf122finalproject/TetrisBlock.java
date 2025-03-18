package org.openjfx.inf122finalproject;

public class TetrisBlock extends Block {
    public TetrisBlock(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "A " + this.name + " Tetris Block";
    }


}
