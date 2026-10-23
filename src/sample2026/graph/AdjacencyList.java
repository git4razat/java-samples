package sample2026.graph;

import java.util.ArrayList;
import java.util.List;

public class AdjacencyList {

    public List<List<Integer>> buildGraph(int n, int edges[][]) {
        List<List<Integer>> graph = new ArrayList<>();
        // vertices with empty adj list
        for(int i=0; i < n; i++) {
           graph.add(new ArrayList<>());
        }
        // populate adj list based on provided edges
        for (int[] edge: edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        return graph;
    }

    public static void main(String[] args) {
        int[][] edges = new int[][] {{0, 1}, {0, 2}, {1, 2}, {1, 3}, {1, 4}, {2, 5}, {3, 4}, {3, 5}};
        int num_of_vertices = 6;
        AdjacencyList adjlist = new AdjacencyList();
        List<List<Integer>> graph = adjlist.buildGraph(num_of_vertices, edges);

        for (int i = 0; i < graph.size(); i++) {
            System.out.print("adj list for : " + i + " :: ");
            List<Integer> edgeList =  graph.get(i);
            for (int edge: edgeList) {
                System.out.print(edge + " ");
            }
            System.out.println("");
        }
    }
}
