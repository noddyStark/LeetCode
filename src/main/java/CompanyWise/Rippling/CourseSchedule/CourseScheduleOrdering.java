package CompanyWise.Rippling.CourseSchedule;


import java.util.*;

/*
* You are given a total of numCourses courses labeled from 0 to numCourses - 1. Each course may have prerequisites courses that must be taken before it.
You're given a list of prerequisite pairs where each element [a, b] means:
You must take course b before taking course a.

Your task is to return an ordering of courses such that all courses can be completed following the given prerequisites:

If multiple valid orderings exist, return any one of them. If it is impossible to complete all courses due to a cycle, return an empty list.

* Input
An integer numCourses representing the total number of courses.
A list prerequisites of length m where each element is a pair [a, b].

* Output
A list of integers representing one valid course ordering, or an empty list if not possible.

Example 1:
Input:
numCourses = 4
prerequisites = [[1, 0], [2, 0], [3, 1], [3, 2]]

Output:
[0, 1, 2, 3] or [0, 2, 1, 3]

Explanation:
Both orders satisfy all prerequisite constraints.
Example 2:
Input:
numCourses = 2
prerequisites = [[1, 0], [0, 1]]

Output:
[]

Explanation:
There’s a cycle: course 0 → 1 → 0. So it's impossible to complete all courses.
Constraints:
1 <= numCourses <= 2000
0 <= prerequisites.length <= 5000
All course indices are in the range [0, numCourses - 1]
There are no duplicate pairs in prerequisites.
* */
public class CourseScheduleOrdering {

    static void main() {
        int numCourses = 4;

        // b -> a
        // 0 -> 1
        // [a, b] means => You must take course b before taking course a.
        int[][] prerequisites = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};

        List<Integer> result = topoSort(prerequisites, numCourses);

        System.out.println("result = " + result);
    }

    private static List<Integer> topoSort(int[][] prerequisites, int numCourses) {


        List<List<Integer>> adjacencyList = createdAdjacencyList(prerequisites, numCourses);

        System.out.println(adjacencyList);

        int[] indegreeArray = indegreeArray(adjacencyList, numCourses);

        System.out.println("indegreeArray = " + Arrays.toString(indegreeArray));

        Queue<Integer> queue = new LinkedList<>();
        List<Integer> result = new ArrayList<>();

        for (int i=0; i<numCourses; i++) {
            if (indegreeArray[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int course = queue.poll();

            result.add(course);

            for (int v : adjacencyList.get(course)) {
                indegreeArray[v]--;

                if (indegreeArray[v] == 0) {
                    queue.offer(v);
                }
            }
        }

        if (result.size() != numCourses) {
            return new ArrayList<>();
        }

        return result;
    }

    private static List<List<Integer>> createdAdjacencyList(int[][] prerequisites, int numCourses) {

        List<List<Integer>> adjacencyList = new ArrayList<>();

        for (int i = 0; i < numCourses; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        for (int[] prerequisite : prerequisites) {
            int preq = prerequisite[0];
            int required = prerequisite[1];

            adjacencyList.get(required).add(preq);
        }

        return adjacencyList;
    }

    private static int[] indegreeArray(List<List<Integer>> adjacencyList, int numCourses) {
        int[] indegree = new int[numCourses];

        for (int course = 0; course < numCourses; course++) {
            for (int nextCourse : adjacencyList.get(course)) {
                indegree[nextCourse]++;
            }
        }

        return indegree;
    }
}
