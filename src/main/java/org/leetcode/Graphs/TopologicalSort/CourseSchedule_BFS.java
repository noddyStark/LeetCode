package org.leetcode.Graphs.TopologicalSort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

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
public class CourseSchedule_BFS {

    public static void main(String[] args) {
        int numCourses = 6;
        int[][] prerequisites = {{1, 0}, {2, 1}, {3, 2}, {4, 3}, {5, 4}};

        boolean result = canFinish(prerequisites, numCourses);
        System.out.println("Can Finish Courses = " + result);
    }

    private static boolean canFinish(int[][] prerequisites, int numCourses) {

        ArrayList<ArrayList<Integer>> adjacencyList = createAdjacencyList(prerequisites, numCourses);
        int[] indegree = createIndegreeArray(adjacencyList, numCourses);
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        int processedCourses = 0;

        while (!queue.isEmpty()) {
            int course = queue.poll();
            processedCourses++;

            for (int nextCourse : adjacencyList.get(course)) {
                indegree[nextCourse]--;

                if (indegree[nextCourse] == 0) {
                    queue.offer(nextCourse);
                }
            }
        }

        return processedCourses == numCourses;
    }

    private static ArrayList<ArrayList<Integer>> createAdjacencyList(int[][] prerequisites, int numCourses) {

        ArrayList<ArrayList<Integer>> adjacencyList = new ArrayList<>();

        for (int i = 0; i < numCourses; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        for (int[] preq : prerequisites) {
            int course = preq[0];
            int prerequisite = preq[1];

            // prerequisite -> course
            adjacencyList.get(prerequisite).add(course);
        }

        return adjacencyList;
    }

    private static int[] createIndegreeArray(ArrayList<ArrayList<Integer>> adjacencyList, int numCourses) {

        int[] indegree = new int[numCourses];

        for (int course = 0; course < numCourses; course++) {
            for (int nextCourse : adjacencyList.get(course)) {
                indegree[nextCourse]++;
            }
        }

        return indegree;
    }
}
