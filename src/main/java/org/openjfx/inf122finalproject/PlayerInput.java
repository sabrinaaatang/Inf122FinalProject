package org.openjfx.inf122finalproject;

import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;

/**
 * Represents player input, either from a keyboard or a mouse.
 */
public class PlayerInput {

    /**
     * Enum representing the source of player input.
     */
    public enum InputSource {
        MOUSE, KEYBOARD
    }

    private final InputSource source;
    private final KeyCode keyCode;
    private double startX, startY, endX, endY;

    /**
     * Constructs a PlayerInput object for keyboard input.
     *
     * @param source  The source of input (KEYBOARD)
     * @param keyCode The key pressed by the player
     */
    public PlayerInput(InputSource source, KeyCode keyCode) {
        this.source = source;
        this.keyCode = keyCode;
        this.startX = 0;
        this.startY = 0;
        this.endX = 0;
        this.endY = 0;
    }

    /**
     * Constructs a PlayerInput object for mouse input.
     *
     * @param source     The source of input (MOUSE)
     * @param startX     The X coordinate where the mouse press started
     * @param startY     The Y coordinate where the mouse press started
     * @param endX       The X coordinate where the mouse was released
     * @param endY       The Y coordinate where the mouse was released
     * @param mouseEvent The mouse event (not stored but used for triggering)
     */
    public PlayerInput(InputSource source, double startX, double startY, double endX, double endY, MouseEvent mouseEvent) {
        this.source = source;
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.keyCode = null;
    }

    /**
     * Gets the input source (MOUSE or KEYBOARD).
     *
     * @return The input source
     */
    public InputSource getSource() {
        return source;
    }

    /**
     * Gets the key code of the pressed key (only for keyboard input).
     *
     * @return The key code if the input is from the keyboard, otherwise null
     */
    public KeyCode getKeyCode() {
        return keyCode;
    }

    /**
     * Gets the starting X coordinate of a mouse input.
     *
     * @return The starting X position
     */
    public double getStartX() {
        return startX;
    }

    /**
     * Gets the starting Y coordinate of a mouse input.
     *
     * @return The starting Y position
     */
    public double getStartY() {
        return startY;
    }

    /**
     * Gets the ending X coordinate of a mouse input.
     *
     * @return The ending X position
     */
    public double getEndX() {
        return endX;
    }

    /**
     * Gets the ending Y coordinate of a mouse input.
     *
     * @return The ending Y position
     */
    public double getEndY() {
        return endY;
    }
}
