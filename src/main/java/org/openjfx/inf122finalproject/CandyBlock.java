package org.openjfx.inf122finalproject;

public class CandyBlock extends Block {
    public CandyBlock(String name) {
        super(name);
        this.autoDropBehavior = new AutoDrop();
    }



    @Override
    public String toString() {
        return "A " + this.name + " Candy Block";
    }


}
