package CompanyWise.Snowflake;


import java.util.ArrayList;
import java.util.Map;

/*
706. Design HashMap
Design a HashMap without using any built-in hash table libraries.

Implement the MyHashMap class:

MyHashMap() initializes the object with an empty map.
void put(int key, int value) inserts a (key, value) pair into the HashMap. If the key already exists in the map, update the corresponding value.
int get(int key) returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key.
void remove(key) removes the key and its corresponding value if the map contains the mapping for the key.


Example 1:

Input
["MyHashMap", "put", "put", "get", "get", "put", "get", "remove", "get"]
[[], [1, 1], [2, 2], [1], [3], [2, 1], [2], [2], [2]]
Output
[null, null, null, 1, -1, null, 1, null, -1]

Explanation
MyHashMap myHashMap = new MyHashMap();
myHashMap.put(1, 1); // The map is now [[1,1]]
myHashMap.put(2, 2); // The map is now [[1,1], [2,2]]
myHashMap.get(1);    // return 1, The map is now [[1,1], [2,2]]
myHashMap.get(3);    // return -1 (i.e., not found), The map is now [[1,1], [2,2]]
myHashMap.put(2, 1); // The map is now [[1,1], [2,1]] (i.e., update the existing value)
myHashMap.get(2);    // return 1, The map is now [[1,1], [2,1]]
myHashMap.remove(2); // remove the mapping for 2, The map is now [[1,1]]
myHashMap.get(2);    // return -1 (i.e., not found), The map is now [[1,1]]


Constraints:

0 <= key, value <= 106
At most 104 calls will be made to put, get, and remove.
*/
public class MyHashMap {

    ArrayList<Integer> listOfIntegers;

    public MyHashMap() {
        listOfIntegers = new ArrayList<>();

        // initialize all indexes with null
        for (int i = 0; i <= 1000000; i++) {
            listOfIntegers.add(null);
        }
    }

    public void put(int key, int value) {
        listOfIntegers.set(key, value);
    }

    public int get(int key) {
        if (listOfIntegers.get(key) == null) {
            return -1;
        }

        return listOfIntegers.get(key);
    }

    public void remove(int key) {
        listOfIntegers.set(key, null);
    }

    static void main() {
        MyHashMap map = new MyHashMap();
        map.put(1, 1);
        map.put(2, 2);
        map.get(1);
        map.get(3);
        map.put(2, 1);
        map.get(2);
        map.remove(2);
        map.get(2);

        System.out.println(map);
    }

    @Override
    public String toString() {
        return "MyHashMap{" +
                "listOfIntegers=" + listOfIntegers +
                '}';
    }
}
