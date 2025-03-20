package org.openjfx.inf122finalproject;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages multiple players in a multiplayer game session.
 * Allows adding, removing, and retrieving players.
 */
public class Multiplayer {
    private List<Player> players;

    /**
     * Constructs a Multiplayer instance with an empty player list.
     */
    public Multiplayer() {
        players = new ArrayList<>();
    }

    /**
     * Adds a player to the multiplayer session.
     *
     * @param player The player to add
     */
    public void addPlayer(Player player) {
        players.add(player);
    }

    /**
     * Removes a player from the multiplayer session.
     *
     * @param player The player to remove
     */
    public void removePlayer(Player player) {
        players.remove(player);
    }

    /**
     * Retrieves the list of players in the session.
     *
     * @return A list of players
     */
    public List<Player> getPlayers() {
        return players;
    }
}
