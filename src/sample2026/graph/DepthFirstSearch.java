package sample2026.graph;

import java.util.ArrayList;
import java.util.List;

public class DepthFirstSearch {

    public List<List<Integer>> buildGraph(int[][] edges, int n) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i =0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge: edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        return graph;
    }

    public List<Integer> dfs(int[][] edges, int n, int src) {
        List<List<Integer>> graph = buildGraph(edges, n);
        List<Integer> dfsResult = new ArrayList<>();
        boolean[] visited = new boolean[n];
        solve(src, dfsResult, visited, graph);
        return dfsResult;
    }

    public void solve(int node, List<Integer> dfsResult, boolean[] visited, List<List<Integer>> graph) {
        dfsResult.add(node);
        visited[node] = true;
        List<Integer> nbrs = graph.get(node);
        for (int nbr: nbrs) {
            if (!visited[nbr]) {
                solve(nbr, dfsResult, visited, graph);
            }
        }
    }

    public static void main(String[] args) {
        int[][] edges = new int[][]{{0, 1}, {0, 2}, {1, 2}, {1, 3}, {1, 4}, {2, 5}, {3, 4}, {3, 5}};
        int n = 6;
        int src = 2;

        DepthFirstSearch dfs = new DepthFirstSearch();

        List<Integer> dfsAnswer = dfs.dfs(edges, n, src);
        for(int node : dfsAnswer) {
            System.out.print(node + " ");
        }
    }
}
