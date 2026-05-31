package CompanyWise.Rippling.LeetCode;

import java.util.*;

/*
399. Evaluate Division

You are given an array of variable pairs equations and an array of real numbers values, where equations[i] = [Ai, Bi]
and values[i] represent the equation Ai / Bi = values[i]. Each Ai or Bi is a string that represents a single variable.

You are also given some queries, where queries[j] = [Cj, Dj] represents the jth query where you must find the answer
for Cj / Dj = ?.

Return the answers to all queries. If a single answer cannot be determined, return -1.0.

Note: The input is always valid. You may assume that evaluating the queries will not result in division by zero and that
 there is no contradiction.

Note: The variables that do not occur in the list of equations are undefined, so the answer cannot be determined for
them.

Example 1:

Input: equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],
["x","x"]]
Output: [6.00000,0.50000,-1.00000,1.00000,-1.00000]
Explanation:
Given: a / b = 2.0, b / c = 3.0
queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
return: [6.0, 0.5, -1.0, 1.0, -1.0 ]
note: x is undefined => -1.0
Example 2:

Input: equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],
["bc","cd"],["cd","bc"]]
Output: [3.75000,0.40000,5.00000,0.20000]
Example 3:

Input: equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
Output: [0.50000,2.00000,-1.00000,-1.00000]
*/
public class EvaluateDivision {

    class Pair {
        String node;
        double val;

        public Pair(String node, double val) {
            this.node = node;
            this.val = val;
        }
    }

    void main() {

        List<List<String>> equations = new ArrayList<>();
        equations.add(List.of("a", "b"));
        equations.add(List.of("b", "c"));

        double[] values = {2.0, 3.0};

        List<List<String>> queries = new ArrayList<>();
        queries.add(List.of("a", "c"));
        queries.add(List.of("b", "a"));
        queries.add(List.of("a", "e"));
        queries.add(List.of("a", "a"));
        queries.add(List.of("x", "x"));

        double[] ans = calcEquation(equations, values, queries);

        System.out.println("ans = " + Arrays.toString(ans));
    }

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {

        Map<String, List<Pair>> adjacencyMap = createAdjacencyMap(equations, values);
        double[] ans = new double[queries.size()];

        for (int i = 0; i < queries.size(); i++) {
            String source = queries.get(i).get(0);
            String target = queries.get(i).get(1);

            if (!adjacencyMap.containsKey(source) || !adjacencyMap.containsKey(target)) {
                ans[i] = -1;
            } else if (source.equals(target)) {
                ans[i] = 1;
            } else {
                Set<String> visited = new HashSet<>();
                ans[i] = dfs(adjacencyMap, source, target, 1.0, visited);
            }

        }

        return ans;
    }

    public double dfs(Map<String, List<Pair>> adjacencyMap, String source, String target, double product, Set<String> visited) {

        if (Objects.equals(source, target)) {
            return product;
        }

        System.out.println("Source = " + source + " target = " + target);

        visited.add(source);

        List<Pair> neighbours = adjacencyMap.get(source);

        for (Pair neighbour : neighbours) {
            System.out.println("neighbour = " + neighbour.node);
            double value = product * neighbour.val;

            System.out.println("product = " + product + " value = " + value);

            if (!visited.contains(neighbour.node)) {

                double result = dfs(adjacencyMap, neighbour.node, target, value, visited);

                System.out.println("result = " + result);

                if (result != -1.0) {
                    return result;
                }
            }
        }

        System.out.println("Returning -1");
        return -1;
    }


    /*
               a
             / (2)
            b
          / (3)
         c
    */
    private Map<String, List<Pair>> createAdjacencyMap(List<List<String>> equations, double[] values) {

        Map<String, List<Pair>> adjacencyMap = new HashMap<>();

        for (int i = 0; i < equations.size(); i++) {
            List<String> eq = equations.get(i);

            String source = eq.get(0);
            String target = eq.get(1);
            double value = values[i];

            adjacencyMap.putIfAbsent(source, new ArrayList<>());
            adjacencyMap.putIfAbsent(target, new ArrayList<>());

            adjacencyMap.get(source).add(new Pair(target, value));
            adjacencyMap.get(target).add(new Pair(source, 1 / value));
        }

        return adjacencyMap;
    }
}
