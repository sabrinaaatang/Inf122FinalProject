package org.openjfx.inf122finalproject;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
public class PlayerInput {
    public enum InputSource { MOUSE, KEYBOARD }

    private final InputSource source;
    private final KeyCode keyCode;
    private double startX, startY, endX, endY;

    public PlayerInput(InputSource source, KeyCode keyCode) {
        this.source = source;
        this.keyCode = keyCode;
        this.startX = 0;
        this.startY = 0;
        this.endX = 0;
        this.endY = 0;
    }
    public PlayerInput(InputSource source, double startX, double startY, double endX, double endY, MouseEvent mouseEvent) {
        this.source = source;
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.keyCode = null;
    }

    public InputSource getSource() {
        return source;
    }

    public KeyCode getKeyCode() {
        return keyCode;
    }

    public double getStartX() {
        return startX;
    }

    public double getStartY() {
        return startY;
    }

    public double getEndX() {
        return endX;
    }

    public double getEndY() {
        return endY;
    }


}
