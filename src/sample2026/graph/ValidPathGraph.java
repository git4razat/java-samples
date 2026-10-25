package sample2026.graph;

import java.util.ArrayList;
import java.util.List;

public class ValidPathGraph {

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

    public boolean solve(int node, int destination, boolean[] visited, List<List<Integer>> graph) {
        if (node == destination) return true;
        visited[node] = true;
        boolean reached = false;
        for (int nbr: graph.get(node)) {
            if (!visited[nbr]) {
                reached = reached || solve(nbr, destination, visited, graph);
                if (reached) return true;
            }
        }
        return reached;
    }

    public boolean validPath(int[][] edges, int n, int source, int destination) {
        List<List<Integer>> graph = buildGraph(edges, n);
        boolean[] visited = new boolean[n];
        return solve(source, destination, visited, graph);
    }
}
