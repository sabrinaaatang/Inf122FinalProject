package org.openjfx.inf122finalproject;

public class PlayerInput {
    public enum InputSource { MOUSE, KEYBOARD }

    private InputSource source;
    private double startX;
    private double startY;
    private double endX;
    private double endY;

    public PlayerInput(InputSource source, double startX, double startY, double endX, double endY) {
        this.source = source;
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
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
}
