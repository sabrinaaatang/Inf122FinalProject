package org.openjfx.inf122finalproject;

import java.util.HashMap;
import java.util.Map;

public class UIConfig {
    static int startRow = 1;
    static int numOfRowCandy = 10;
    static int numOfColCandy = 10;

    static int numOfRowTetris = 20;
    static int numOfColTetris = 10;

    static WindowSize windowSize = new WindowSize(1280, 1280);
    static int block_width = 60;
    static int block_height = 60;

    static int tetrisBlockWidth = 38;
    static int tetrisBlockHeight = 38;

}

class WindowSize {
    int width;
    int height;

    WindowSize(int width, int height) {
        this.width = width;
        this.height = height;
    }
}