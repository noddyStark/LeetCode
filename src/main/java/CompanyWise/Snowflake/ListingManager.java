package CompanyWise.Snowflake;

import java.util.*;

public class ListingManager {

    // Map listing ID -> current score (installation count)
    private Map<String, Integer> idToScore = new HashMap<>();

    // Map score -> set of listing IDs having that score, scores sorted descending
    private TreeMap<Integer, Set<String>> scoreToIds = new TreeMap<>(Collections.reverseOrder());

    /**
     * Updates listing counts in batch and returns the top K listings (with their counts) descending.
     */
    public List<Map.Entry<String, Integer>> updateAndGetTopK(Map<String, Integer> updates, int k) {
        // Apply all updates
        for (Map.Entry<String, Integer> entry : updates.entrySet()) {
            String id = entry.getKey();
            int newScore = entry.getValue();

            // remove ID from old score set, if present
            if (idToScore.containsKey(id)) {
                int oldScore = idToScore.get(id);
                Set<String> ids = scoreToIds.get(oldScore);
                ids.remove(id);
                if (ids.isEmpty()) {
                    scoreToIds.remove(oldScore);
                }
            }

            if (newScore > 0) {
                // update idToScore with new score
                idToScore.put(id, newScore);
                // add ID to set for new score
                scoreToIds.computeIfAbsent(newScore, x -> new TreeSet<>()).add(id);
            } else {
                // If newScore is zero, remove it from idToScore as well
                idToScore.remove(id);
            }
        }

        // Collect top K as (ID, score) pairs, ordered descending by score then by ID
        List<Map.Entry<String, Integer>> result = new ArrayList<>();
        for (Map.Entry<Integer, Set<String>> entry : scoreToIds.entrySet()) {
            int score = entry.getKey();
            // TreeSet for lex order tie-breaking
            for (String id : entry.getValue()) {
                result.add(new AbstractMap.SimpleEntry<>(id, score));
                if (result.size() == k) return result;
            }
        }
        return result;
    }

    // Example main for illustration/testing
    public static void main(String[] args) {
        ListingManager manager = new ListingManager();
        Map<String, Integer> updates = new HashMap<>();
        updates.put("A", 50);
        updates.put("B", 70);
        updates.put("C", 70);
        updates.put("D", 60);

        // First update, get top 3
        List<Map.Entry<String, Integer>> top3 = manager.updateAndGetTopK(updates, 3);
        System.out.println("After first update (top 3):");
        for (Map.Entry<String, Integer> entry : top3) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        // Do another update (update A to 80, remove D)
        Map<String, Integer> updates2 = new HashMap<>();
        updates2.put("A", 80);
        updates2.put("D", 0); // removal
        List<Map.Entry<String, Integer>> top2 = manager.updateAndGetTopK(updates2, 2);
        System.out.println("After second update (top 2):");
        for (Map.Entry<String, Integer> entry : top2) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}