package org.openjfx.inf122finalproject;

public abstract class BlockType {
    protected String type;

    public BlockType(String type) {
        this.type = type;
    }

    // candy/jewel type...
    public String getType() {
        return type;
    }
}