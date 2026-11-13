package sample2026.neetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CourseSchedule {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < numCourses; i++) {
            graph.add(i, new ArrayList<>());
        }

        int[] inDegree = new int[numCourses];

        for (int[] preq: prerequisites) {
            int course = preq[0];
            int prerequisite = preq[1];
            graph.get(prerequisite).add(course);
            inDegree[course]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) queue.add(i);
        }

        int completedCourses = 0;

        while(!queue.isEmpty()) {
            int course = queue.poll();
            completedCourses++;
            List<Integer> nextCourses = graph.get(course);
            for (int nextCourse: nextCourses) {
                inDegree[nextCourse]--;
                if (inDegree[nextCourse] == 0) {
                    queue.add(nextCourse);
                }
            }
        }
        return completedCourses == numCourses;
    }

    public static void main(String[] args) {
        CourseSchedule solution = new CourseSchedule();

        // Test Case 1: Valid schedule (0 -> 1 -> 2, and 0 -> 2)
        int numCourses1 = 3;
        int[][] prerequisites1 = {{1, 0}, {2, 0}, {2, 1}};
        System.out.println("Test Case 1 (Expected: true): " + solution.canFinish(numCourses1, prerequisites1));

        // Test Case 2: Cycle exists (0 -> 1 and 1 -> 0)
        int numCourses2 = 2;
        int[][] prerequisites2 = {{1, 0}, {0, 1}};
        System.out.println("Test Case 2 (Expected: false): " + solution.canFinish(numCourses2, prerequisites2));

        // Test Case 3: No prerequisites at all
        int numCourses3 = 4;
        int[][] prerequisites3 = {};
        System.out.println("Test Case 3 (Expected: true): " + solution.canFinish(numCourses3, prerequisites3));
    }
}
