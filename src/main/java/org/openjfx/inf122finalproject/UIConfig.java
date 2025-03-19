package org.openjfx.inf122finalproject;

import java.util.HashMap;
import java.util.Map;

public class UIConfig {
    static int startRow = 1;
    static int numOfRowCandy = 8;
    static int numOfColCandy = 5;

    static int numOfRowTetris = 20;
    static int numOfColTetris = 10;

    static WindowSize windowSize = new WindowSize(1280, 1280);
    static int candySize = 50;

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