import java.util.HashMap;
import java.util.Map;

public class ScoreManager {

    private Multiplayer multiplayer;
    private Map<Player, Integer> playerToScore;

    public ScoreManager() {
        multiplayer = new Multiplayer();
        playerToScore = new HashMap<>();
    }

    public void updateScore(Player player, int points) {
        playerToScore.put(player, points);
    }

    public int getScore(Player player) {
        return playerToScore.get(player);
    }

    public void resetScore() {
        for (Map.Entry<Player, Integer> entry : playerToScore.entrySet()) {
            entry.setValue(0);
        }   
    }
}
