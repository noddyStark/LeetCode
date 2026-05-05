package org.leetcode.Graphs.TopologicalSort;

import java.util.ArrayList;
import java.util.Arrays;

/*
207. Course Schedule

There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must
take course b(i) first if you want to take course a(i).

For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
Return true if you can finish all courses. Otherwise, return false.

Example 1:

Input: numCourses = 2, prerequisites = [[1,0]]
Output: true
Explanation: There are a total of 2 courses to take.
To take course 1 you should have finished course 0. So it is possible.
Example 2:

Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take.

To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1.
So it is impossible.
* */
public class CourseSchedule_DFS {

    static void main() {
        int numCourses = 2;
        int[][] prerequisites = {
                {1, 0},
                {0, 1}
        };

        /* adjacencyList = [
         *       0 →  [1],
         *       1 →  [0]
         *     ]
         */

        boolean result = canFinish(numCourses, prerequisites);

        System.out.println("can finish courses = " + result);

    }

    private static boolean canFinish(int numCourses, int[][] prerequisites) {
        ArrayList<ArrayList<Integer>> adjacencyList = createAdjacencyList(numCourses, prerequisites);

        boolean[] visited = new boolean[numCourses];
        boolean[] pathVisited = new boolean[numCourses];

        for (int course = 0; course < numCourses; course++) {
            if (!visited[course]) {
                if (hasCycle(adjacencyList, visited, pathVisited, course)) {
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean hasCycle(ArrayList<ArrayList<Integer>> adjacencyList,
                             boolean[] visited,
                             boolean[] pathVisited,
                             int course) {

        visited[course] = true;
        pathVisited[course] = true;

        for (int nextCourse : adjacencyList.get(course)) {
            if (!visited[nextCourse]) {
                if (hasCycle(adjacencyList, visited, pathVisited, nextCourse)) {
                    return true;
                }
            } else if (pathVisited[nextCourse]) {
                System.out.println("pathVisited = " + Arrays.toString(pathVisited));
                return true;
            }
        }

        pathVisited[course] = false;
        return false;
    }

    private static ArrayList<ArrayList<Integer>> createAdjacencyList(int numCourses, int[][] prerequisites) {
        ArrayList<ArrayList<Integer>> adjacencyList = new ArrayList<>();

        for (int i=0; i<numCourses; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        for (int i = 0; i < numCourses; i++) {
            int[] courses = prerequisites[i];

            int course = courses[0];
            int prereq = courses[1];

            adjacencyList.get(prereq).add(course);
        }

        return adjacencyList;
    }

}
