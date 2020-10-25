package sample;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

class GNode implements Comparator<GNode> {
	public int node;
	public int cost;

	public GNode() {
	} // empty constructor

	public GNode(int node, int cost) {
		this.node = node;
		this.cost = cost;
	}

	@Override
	public int compare(GNode node1, GNode node2) {
		if (node1.cost < node2.cost)
			return -1;
		if (node1.cost > node2.cost)
			return 1;
		return 0;
	}
}


public class GraphShortestDistance {

	int dist[];
	Set<Integer> visited;
	PriorityQueue<GNode> pqueue;
	int V; // Number of vertices
	List<List<GNode>> adj_list;

	public GraphShortestDistance(int V) {
		this.V = V;
		dist = new int[V];
		visited = new HashSet<Integer>();
		pqueue = new PriorityQueue<GNode>(V, new GNode());
	}

	// Dijkstra's Algorithm implementation
	public void algo_dijkstra(List<List<GNode>> adj_list, int src_vertex) {
		this.adj_list = adj_list;

		for (int i = 0; i < V; i++)
			dist[i] = Integer.MAX_VALUE;

		// first add source vertex to PriorityQueue
		pqueue.add(new GNode(src_vertex, 0));

		// Distance to the source from itself is 0
		dist[src_vertex] = 0;
		while (visited.size() != V) {

			// u is removed from PriorityQueue and has min distance
			int u = pqueue.remove().node;

			// add node to finalized list (visited)
			visited.add(u);
			graph_adjacentNodes(u);
		}
	}

	// this methods processes all neighbours of the just visited node
	private void graph_adjacentNodes(int u) {
		int edgeDistance = -1;
		int newDistance = -1;

		// process all neighbouring nodes of u
		for (int i = 0; i < adj_list.get(u).size(); i++) {
			GNode v = adj_list.get(u).get(i);

			// proceed only if current node is not in 'visited'
			if (!visited.contains(v.node)) {
				edgeDistance = v.cost;
				newDistance = dist[u] + edgeDistance;

				// compare distances
				if (newDistance < dist[v.node])
					dist[v.node] = newDistance;

				// Add the current vertex to the PriorityQueue
				pqueue.add(new GNode(v.node, dist[v.node]));
			}
		}
	}

	public static void main(String[] args) {
		int V = 6;
		int source = 0;
		// adjacency list representation of graph
		List<List<GNode>> adj_list = new ArrayList<List<GNode>>();
		// Initialize adjacency list for every node in the graph
		for (int i = 0; i < V; i++) {
			List<GNode> item = new ArrayList<GNode>();
			adj_list.add(item);
		}
		// Input graph edges
		adj_list.get(0).add(new GNode(1, 5));
		adj_list.get(0).add(new GNode(2, 3));
		adj_list.get(0).add(new GNode(3, 2));
		adj_list.get(0).add(new GNode(4, 3));
		adj_list.get(0).add(new GNode(5, 3));
		adj_list.get(2).add(new GNode(1, 2));
		adj_list.get(2).add(new GNode(3, 3));

		// call Dijkstra's algo method
		GraphShortestDistance dpq = new GraphShortestDistance(V);
		dpq.algo_dijkstra(adj_list, source);

		// Print the shortest path from source node to all the nodes
		System.out.println("The shorted path from source node to other nodes:");
		System.out.println("Source\t\t" + "Node#\t\t" + "Distance");
		for (int i = 0; i < dpq.dist.length; i++)
			System.out.println(source + " \t\t " + i + " \t\t " + dpq.dist[i]);

	}

}
