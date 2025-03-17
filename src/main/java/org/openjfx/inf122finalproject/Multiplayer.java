package org.openjfx.inf122finalproject;

import java.util.ArrayList;
import java.util.List;

public class Multiplayer {
    private List<Player> players;

    public Multiplayer() {
        players = new ArrayList<>();
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void removePlayer(Player player) {
        players.remove(player);
    }

    public List<Player> getPlayers() {
        return players;
    }
}
