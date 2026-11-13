package sample2026.neetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MinimumSemesters {

    public int minimumSemesters(int n, int[][] relations) {
        List<List<Integer>> graph = new ArrayList<>();

        for (int i =0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        int[] inDegree = new int[n+1];
        for(int[] relation: relations) {
           int prevCourse = relation[0];
           int nextCourse = relation[1];
           graph.get(prevCourse).add(nextCourse);
           inDegree[nextCourse]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if(inDegree[i] == 0) {
                queue.add(i);
            }
        }

        int completedCourses = 0;
        int semesters = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
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
            semesters++;
        }
        return (completedCourses == n) ? semesters : -1;
    }
}
