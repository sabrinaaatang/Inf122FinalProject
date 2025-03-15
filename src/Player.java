public class Player {
    private int playerID;
    private String name;

    public Player(int playerID, String name) {
        this.playerID = playerID;
        this.name = name;
    }

    public int getID() {
        return playerID;
    }

    public void setID(int playerID) {
        this.playerID = playerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
