package org.openjfx.inf122finalproject;

import javafx.beans.property.ObjectProperty;

/** destroy a block and replace them with empty block */
public class BlockAnnihilation implements BlockManipulator{
    private ObjectProperty<Block[][]> blocks;
    private Position pos;

    public BlockAnnihilation(ObjectProperty<Block[][]> blocks, Position pos) {
        this.blocks = blocks;
        this.pos = pos;
    }

    @Override
    public void manipulate() {

    }
}
