package org.openjfx.inf122finalproject;

public class Player {
    private int playerID;
    private String name;

    public Player(int playerID, String name) {
        this.playerID = playerID;
        this.name = name;
    }

    public int getPlayerID() {
        return playerID;
    }

    public String getName() {
        return name;
    }
}
