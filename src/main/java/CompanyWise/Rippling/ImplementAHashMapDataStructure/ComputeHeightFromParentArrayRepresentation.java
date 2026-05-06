package CompanyWise.Rippling.ImplementAHashMapDataStructure;

import java.util.*;

/*
*
*
You are given a tree represented as a parent array, where each index corresponds to a node, and the value at that index represents its parent.
The root node is indicated by a value of -1.

Your task is to compute the height of the tree, which is the number of nodes along the longest path from the root to any leaf.

Input:
An array parents of length n where:

parents[i] is the parent of node i.
Exactly one element has a value of -1 indicating the root.
Output:
An integer representing the height of the tree.
*
* */
public class ComputeHeightFromParentArrayRepresentation {

    static void main() {

        int[] parents = {4, 3, 0, 6, 6, 3, -1, 0};

        int height = heightOfTree(parents);

        System.out.println("heigh = " + height);

    }

    private static int heightOfTree(int[] parents) {

        Map<Integer, List<Integer>> parentsToChild = new HashMap<>();

        int root = -1;

        for (int child = 0; child < parents.length; child++) {
            int parent = parents[child];

            if (parents[child] == -1) {
                root = child;
            } else {
                parentsToChild.computeIfAbsent(parent, k -> new ArrayList<>()).add(child);
            }
        }

        /*
        *       6
             /     \
            3       4
           / \     /
          1   5   0
                 / \
                2   7
        * */

        System.out.println(parentsToChild);
        /*
         * {     0=[2, 7],
         *       3=[1, 5],
         *       4=[0],
         *       6=[3, 4]
         * }
         * */

        return dfs(root, parentsToChild);
    }

    /*
    * 6
        ├── 3
        │   ├── 1
        │   └── 5
        └── 4
            └── 0
                ├── 2
                └── 7
    * */
    public static int dfs(int node, Map<Integer, List<Integer>> parentsToChild) {

        int maxChildHeight = 0;

        if (parentsToChild.get(node) != null) {
            for (int child : parentsToChild.get(node)) {
                int childHeight = dfs(child, parentsToChild);
                maxChildHeight = Math.max(childHeight, maxChildHeight);
            }
        }

        return 1 + maxChildHeight;
    }
}
