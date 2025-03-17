package org.openjfx.inf122finalproject;

public class CandyBlock extends Block implements AutoDropBehavior{
    public CandyBlock(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "A " + this.name + " Candy Block";
    }

    @Override
    public void drop() {

    }
}
