package com.ds.algo;

import java.util.Iterator;
import java.util.LinkedList;

public class GraphDFS {

	private int V;

	LinkedList<Integer> adjList[];

	GraphDFS(int V) {
		this.V = V;
		adjList = new LinkedList[V];
		for (int i = 0; i < V; i++) {
			adjList[i] = new LinkedList<Integer>();
		}
	}

	void addEdge(int src, int dest) {
		adjList[src].add(dest);
	}

	void printGraph(GraphDFS graph) {
		for (int v = 0; v < graph.V; v++) {
			System.out.println("Adjacency list of vertex " + v);
			System.out.print("head");
			Iterator<Integer> itr  = graph.adjList[v].listIterator();
			while(itr.hasNext()) {
				int n =  itr.next();
				System.out.print(" -> " + n);
			}
			System.out.println("\n");
		}
	}

	// A function used by DFS
	void DFSUtil(int v, boolean visited[]) {
		// Mark the current node as visited and print it
		visited[v] = true;
		System.out.print(v + " ");
	
		// Recur for all the vertices adjacent to this vertex
		Iterator<Integer> i = adjList[v].listIterator();
		while (i.hasNext()) {
			int n = i.next();
			if (!visited[n])
				DFSUtil(n, visited);
		}
	}

	// The function to do DFS traversal. It uses recursive DFSUtil()
	void DFS() {
		// Mark all the vertices as not visited(set as
		// false by default in java)
		boolean visited[] = new boolean[V];

		// Call the recursive helper function to print DFS traversal
		// starting from all vertices one by one
		for (int i = 0; i < V; ++i)
			if (visited[i] == false)
				DFSUtil(i, visited);
	}

	// prints BFS traversal from a given source s
	void BFS(int s) {
		// Mark all the vertices as not visited(By default
		// set as false)
		boolean visited[] = new boolean[V];

		// Create a queue for BFS
		LinkedList<Integer> queue = new LinkedList<Integer>();

		// Mark the current node as visited and enqueue it
		visited[s] = true;
		queue.add(s);

		while (queue.size() != 0) {
			// Dequeue a vertex from queue and print it
			s = queue.poll();
			System.out.print(s + " ");

			// Get all adjacent vertices of the dequeued vertex s
			// If a adjacent has not been visited, then mark it
			// visited and enqueue it
			Iterator<Integer> i = adjList[s].listIterator();
			while (i.hasNext()) {
				int n = i.next();
				if (!visited[n]) {
					visited[n] = true;
					queue.add(n);
				}
			}
		}
	}

	public static void main(String[] args) {
		GraphDFS g = new GraphDFS(4);
		g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(1, 2);
		g.addEdge(2, 0);
		g.addEdge(2, 3);
		g.addEdge(3, 3);
		g.printGraph(g);

		g.DFS();
		System.out.println("");
		System.out.println("======================");
		g.BFS(2);
	}

}
