package CompanyWise.Snowflake;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class DataMarketplaceListings {

    // Map listing ID -> current score (installation count)
    private Map<String, Integer> idToScore = new HashMap<>();

    // Map score -> set of listing IDs having that score, scores sorted descending
    private TreeMap<Integer, Set<String>> scoreToIds = new TreeMap<>();

    static void main() {

        Map<String, Integer> updates = new HashMap<>();
        updates.put("Apple", 10);
        updates.put("Banana", 20);
        updates.put("Orange", 30);
        updates.put("Strawberry", 40);
        updates.put("Watermelon", 50);
        updates.put("Peach", 60);

        List<Map.Entry<String, Integer>> result = updateAndGetTopK(updates, 3);

    }


    private static List<Map<String, Integer>> updateAndGetTopK(Map<String, Integer> updates, int k) {

    }
}
