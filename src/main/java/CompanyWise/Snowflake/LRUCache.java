package CompanyWise.Snowflake;

import java.util.LinkedHashMap;

public class LRUCache {

    LinkedHashMap<Integer, Integer> linkedHashMap;
    int size;

    public LRUCache(int capacity) {
        linkedHashMap = new LinkedHashMap<>(capacity, 0.75f, true);
        size = capacity;
    }

    public int get(int key) {
        if (!linkedHashMap.containsKey(key)) {
            return -1;
        }
        return linkedHashMap.get(key);
    }

    public void print() {
        System.out.println(linkedHashMap);
    }

    public void put(int key, int value) {
        linkedHashMap.put(key, value);

        if (linkedHashMap.size() > size) {
            Integer leastRecentlyUsedKey = linkedHashMap.keySet().iterator().next();
            linkedHashMap.remove(leastRecentlyUsedKey);
        }
    }

    static void main() {
        LRUCache lRUCache = new LRUCache(2);

        lRUCache.put(1, 1); // cache is {1=1}
        lRUCache.put(2, 2); // cache is {1=1, 2=2}
        System.out.println("lRUCache.get(1) = " + lRUCache.get(1));    // return 1
        lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
        lRUCache.print();
        System.out.println("lRUCache.get(2) = " + lRUCache.get(2));     // returns -1 (not found)
        lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
        lRUCache.print();
        System.out.println("lRUCache.get(1) = " + lRUCache.get(1));     // returns -1 (not found)
        System.out.println("lRUCache.get(3) = " + lRUCache.get(3));     // return 3
        System.out.println("lRUCache.get(4) = " + lRUCache.get(4));     // return 4

        lRUCache.print();

    }
}
