package sample;

import java.util.Iterator;

//Java program to print BFS traversal from a given source vertex. 
//BFS(int s) traverses vertices reachable from s. 

import java.util.LinkedList;
import java.util.Stack;

public class GraphOfIntegers {

	private int V; // No. of vertices
	private LinkedList<Integer> adj[]; // Adjacency Lists

	// Constructor
	GraphOfIntegers(int v) {
		V = v;
		adj = new LinkedList[v];
		for (int i = 0; i < v; ++i)
			adj[i] = new LinkedList();
	}

	// Function to add an edge into the graph
	void addEdge(int v, int w) {
		adj[v].add(w);
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
			Iterator<Integer> i = adj[s].listIterator();
			while (i.hasNext()) {
				int n = i.next();
				if (!visited[n]) {
					visited[n] = true;
					queue.add(n);
				}
			}
		}
	}

	void DFS(int s) {
		// Initially mark all vertices as not visited
		boolean[] visited = new boolean[V];

		// Create a stack for DFS
		Stack<Integer> stack = new Stack<>();

		// Push the current source node
		stack.push(s);

		while (stack.empty() == false) {
			// Pop a vertex from stack and print it
			s = stack.pop();

			// Stack may contain same vertex twice. So
			// we need to print the popped item only
			// if it is not visited.
			if (!visited[s]) {
				System.out.print(s + " ");
				visited[s] = true;
			}

			// Get all adjacent vertices of the popped vertex s
			// If a adjacent has not been visited, then push it
			// to the stack.
			Iterator<Integer> itr = adj[s].iterator();
			while (itr.hasNext()) {
				int v = itr.next();
				if (!visited[v])
					stack.push(v);
			}

		}
	}

	public boolean hasCycleUtil(int i, boolean[] visited, boolean[] recStack) {
		if (recStack[i]) {
			return true;
		}

		if (visited[i]) {
			return false;
		}

		recStack[i] = true;
		visited[i] = true;

		LinkedList<Integer> children = adj[i];

		for (int k = 0; k < children.size(); k++) {
			if (hasCycleUtil(children.get(k), visited, recStack)) {
				return true;
			}
		}

		recStack[i] = false;
		return false;
	}

	/*
	 * Cycle Detection in Directed Graph using DFS TC - O(V+E) if a node is in
	 * recursion stack, its a cycle we will maintain the viisted array and recstack
	 * array in this case
	 */
	public boolean hasCycle() {

		boolean[] visited = new boolean[V];
		boolean[] recStack = new boolean[V];

		for (int i = 0; i < V; i++) {
			if (hasCycleUtil(i, visited, recStack)) {
				return true;
			}
		}
		return false;

	}

	boolean isCyclicUtilUD(int v, boolean visited[], int parent) {
		// Mark the current node as visited
		visited[v] = true;
		Integer i;

		// Recur for all the vertices adjacent to this vertex
		Iterator<Integer> it = adj[v].iterator();
		while (it.hasNext()) {
			i = it.next();

			// If an adjacent is not visited, then recur for that
			// adjacent
			if (!visited[i]) {
				if (isCyclicUtilUD(i, visited, v))
					return true;
			}

			// If an adjacent is visited and not parent of current
			// vertex, then there is a cycle.
			else if (i != parent)
				return true;
		}
		return false;
	}

	/*
	 * Cycle Detection in UnDirected Graph using DFS TC - O(V+E) If a cell is in
	 * visited array but not parent of node coming from, its a cycle we will
	 * maintain visited array and parent variable in this case
	 */
	boolean isCyclicUD() {
		// Mark all the vertices as not visited
		boolean visited[] = new boolean[V];

		// Call the recursive helper function to detect cycle in
		// different DFS trees
		for (int u = 0; u < V; u++)
			if (!visited[u]) // Don't recur for u if already visited
				if (isCyclicUtilUD(u, visited, -1))
					return true;

		return false;
	}

	// Driver method to
	public static void main(String args[]) {
		GraphOfIntegers g = new GraphOfIntegers(4);

		g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(1, 2);
		g.addEdge(2, 0);
		g.addEdge(2, 3);
		g.addEdge(3, 3);

		System.out.println("Following is Breadth First Traversal " + "(starting from vertex 2)");

		g.BFS(2);
	}
}