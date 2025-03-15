public class ScoreManager {

    private Multiplayer multiplayer;

    public ScoreManager() {
        multiplayer = new Multiplayer();
    }

    public void updateScore(Player player, int points) {
        for (Player currPlayer : multiplayer.getPlayers()) {
            if (currPlayer == player) {
                currPlayer.setScore(points);
            }
        }
    }

    public int getScore(Player player) {
        for (Player currPlayer : multiplayer.getPlayers()) {
            if (currPlayer == player) {
                return currPlayer.getScore();
            }
        }

        return 0;
    }

    public void resetScore() {
        for (Player currPlayer : multiplayer.getPlayers()) {
            currPlayer.setScore(0);
        } 
    }
}
