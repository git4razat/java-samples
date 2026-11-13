package sample2026.neetcode;

import java.sql.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CourseScheduleII {

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(i, new ArrayList<>());
        }

        int[] inDegree = new int[numCourses];
        for (int[] prerequisite: prerequisites) {
            int course = prerequisite[0];
            int preq =  prerequisite[1];
            graph.get(preq).add(course);
            inDegree[course]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) queue.add(i);
        }

        int[] order = new int[numCourses];
        int completedCourses = 0;

        while(!queue.isEmpty()) {
            int course = queue.poll();
            order[completedCourses] = course;
            completedCourses++;
            List<Integer> nextCourses = graph.get(course);
            for (int nextCourse: nextCourses) {
                inDegree[nextCourse]--;
                if (inDegree[nextCourse] == 0) {
                    queue.add(nextCourse);
                }
            }
        }
        return (completedCourses == numCourses) ? order : new int[0];
    }
}
