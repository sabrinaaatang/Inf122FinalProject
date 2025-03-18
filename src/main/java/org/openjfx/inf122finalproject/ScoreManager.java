package org.openjfx.inf122finalproject;

import java.util.HashMap;
import java.util.Map;

public class ScoreManager {
    private Map<Player, Integer> scores;

    public ScoreManager() {
        scores = new HashMap<>();
    }

    public void updateScore(Player player, int points) {
        scores.put(player, scores.getOrDefault(player, 0) + points);
    }

    public void resetScore() {
        scores.clear();
    }

    public int getScore(Player player) {
        return scores.getOrDefault(player, 0);
    }
}
