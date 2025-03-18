package org.openjfx.inf122finalproject;
import javafx.scene.input.MouseEvent;
public class PlayerInput {
    public enum InputSource { MOUSE, KEYBOARD }

    private InputSource source;
    private double startX;
    private double startY;
    private double endX;
    private double endY;
    private MouseEvent mouseEvent;

    public PlayerInput(InputSource source, double startX, double startY, double endX, double endY, MouseEvent mouseEvent) {
        this.source = source;
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.mouseEvent = mouseEvent;
    }

    public InputSource getSource() {
        return source;
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

    public MouseEvent getMouseEvent() {
        return mouseEvent;
    }
}
