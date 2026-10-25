package sample2026.graph;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class ConnectedComponentsGraph {

    public List<List<Integer>> buildGraph(int[][] edges, int n) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i=0;i<n;i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge: edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        return graph;
    }

    public void solve(int node, boolean[] visited, List<List<Integer>> graph) {
        visited[node] = true;
        for(int nbr: graph.get(node)) {
            if(!visited[nbr]) {
                solve(nbr, visited, graph);
            }
        }
    }

    public int numOfConnectedComponents(int[][] edges, int n) {
        List<List<Integer>> graph = buildGraph(edges, n);
        boolean[] visited = new boolean[n];
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
               solve(i, visited, graph);
               count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[][] edges = new int[][]{{0, 1}, {0, 2}, {1, 2}, {1, 3}, {1, 4}, {2, 5}, {3, 4}, {3, 5}, {6, 7}, {7, 8}, {9,10}};
        int n = 11;

        ConnectedComponentsGraph conn = new ConnectedComponentsGraph();
        int result = conn.numOfConnectedComponents(edges, n);
        System.out.println("result::" + result);

        edges = new int[][]{{0, 1}, {0, 2}, {1, 2}, {1, 3}, {1, 4}, {2, 5}, {3, 4}, {3, 5}, {6, 7}, {7, 8}};
        n = 9;
        result = conn.numOfConnectedComponents(edges, n);
        System.out.println("result::" + result);


        edges = new int[][]{{0, 1}, {0, 2}, {1, 2}, {1, 3}, {1, 4}, {2, 5}, {3, 4}, {3, 5}};
        n = 6;
        result = conn.numOfConnectedComponents(edges, n);
        System.out.println("result::" + result);
    }
}
