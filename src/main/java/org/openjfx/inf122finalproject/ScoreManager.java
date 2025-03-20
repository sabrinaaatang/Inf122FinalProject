package org.openjfx.inf122finalproject;

import java.util.HashMap;
import java.util.Map;

/**
 * Manages player scores in the game.
 * Allows updating, retrieving, and resetting scores for players.
 */
public class ScoreManager {
    private Map<Player, Integer> scores;

    /**
     * Constructs a ScoreManager with an empty score map.
     */
    public ScoreManager() {
        scores = new HashMap<>();
    }

    /**
     * Updates the score for a given player by adding the specified points.
     *
     * @param player The player whose score needs to be updated
     * @param points The number of points to add
     */
    public void updateScore(Player player, int points) {
        scores.put(player, scores.getOrDefault(player, 0) + points);
    }

    /**
     * Resets all player scores by clearing the score map.
     */
    public void resetScore() {
        scores.clear();
    }

    /**
     * Retrieves the score of a given player.
     *
     * @param player The player whose score is requested
     * @return The player's current score, or 0 if they have no recorded score
     */
    public int getScore(Player player) {
        return scores.getOrDefault(player, 0);
    }
}
