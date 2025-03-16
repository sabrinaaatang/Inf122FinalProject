package org.openjfx.demo;

import java.util.HashMap;
import java.util.Map;

public class UIConfig {
    static int startRow = 1;
    static int numOfRow = 10;
    static int numOfCol = 10;
    static WindowSize windowSize = new WindowSize(1024, 768);
    static int block_width = 60;
    static int block_height = 60;

}

class WindowSize {
    int width;
    int height;

    WindowSize(int width, int height) {
        this.width = width;
        this.height = height;
    }
}