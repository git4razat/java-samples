package sample2026.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BreadthFirstSearch {

    public List<List<Integer>> buildGraph(int[][] edges, int n) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0 ; i < n ; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge: edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        return graph;
    }

    public int[] breadthFirstSearch(int[][] edges, int n, int src) {
        List<List<Integer>> graph = buildGraph(edges, n);
        int[] bfsResult = new int[n];
        boolean[] visited = new boolean[n];

        Queue<Integer> queue = new LinkedList<>();
        queue.add(src);
        visited[src] = true;
        int count = 0;

        while(!queue.isEmpty()) {
            int current = queue.poll();
            bfsResult[count] = current;
            List<Integer> nbrs = graph.get(current);
            for (int nbr: nbrs) {
                if (!visited[nbr]) {
                    queue.add(nbr);
                    visited[nbr] = true;
                }
            }
            count++;
        }
        return bfsResult;
    }


    public static void main(String[] args) {
        int[][] edges = new int[][]{{0, 1}, {0, 2}, {1, 2}, {1, 3}, {1, 4}, {2, 5}, {3, 4}, {3, 5}};
        int n = 6;
        int src = 0;

        BreadthFirstSearch bfs = new BreadthFirstSearch();

        int[] bfsAnswer = bfs.breadthFirstSearch(edges, n, src);
        for(int node : bfsAnswer) {
            System.out.println(node + " ");
        }
    }


}
