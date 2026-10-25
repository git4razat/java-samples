package sample2026.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AllPathSourceToDestination {

    enum Color {
        WHITE,
        GREY,
        BLACK
    }

    private List<List<Integer>> buildGraph(int[][] edges, int n) {
       List<List<Integer>> graph = new ArrayList<>();
       for (int i= 0; i < n; i++) {
           graph.add(new ArrayList<>());
       }

       for (int[] edge: edges) {
           graph.get(edge[0]).add(edge[1]);
           graph.get(edge[1]).add(edge[0]);
       }

       return graph;
    }

    boolean dfs(int node, int destination, List<List<Integer>> graph, Color[] state) {
        if (state[node] != Color.WHITE) {
            return state[node] == Color.BLACK;
        }

        if (graph.get(node).isEmpty()) return node == destination;

        state[node] = Color.GREY;

        for (int nbr: graph.get(node)) {
            if (!dfs(nbr, destination, graph, state)) return false;
        }

        state[node] = Color.BLACK;
        return true;
    }

    public boolean leadsToDestination(int n, int[][] edges, int source, int destination) {
        List<List<Integer>> graph = buildGraph(edges, n);
        Color[] state = new Color[n];
        Arrays.fill(state, Color.WHITE);
        return dfs(source, destination, graph, state);
    }
}
