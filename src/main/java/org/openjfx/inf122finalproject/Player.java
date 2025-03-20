package org.openjfx.inf122finalproject;

/**
 * Represents a player in the game.
 * Each player has a unique ID and a name.
 */
public class Player {
    private int playerID;
    private String name;

    /**
     * Constructs a Player with a given ID and name.
     *
     * @param playerID The unique identifier for the player
     * @param name     The name of the player
     */
    public Player(int playerID, String name) {
        this.playerID = playerID;
        this.name = name;
    }

    /**
     * Retrieves the player's unique ID.
     *
     * @return The player's ID
     */
    public int getPlayerID() {
        return playerID;
    }

    /**
     * Retrieves the player's name.
     *
     * @return The player's name
     */
    public String getName() {
        return name;
    }
}
