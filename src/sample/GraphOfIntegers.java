package sample;

import java.util.ArrayList;
import java.util.Iterator;

//Java program to print BFS traversal from a given source vertex. 
//BFS(int s) traverses vertices reachable from s. 

import java.util.LinkedList;
import java.util.Stack;

public class GraphOfIntegers {

	private int V; // No. of vertices
	private LinkedList<Integer>[] adj; // Adjacency Lists

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
		boolean visited[] = new boolean[V];
		LinkedList<Integer> queue = new LinkedList<Integer>();
		// Mark the current node as visited and enqueue it
		visited[s] = true;
		queue.add(s);
		while (queue.size() != 0) {
			s = queue.poll();
			System.out.print(s + " ");
			// Get all adjacent vertices of the dequeued vertex s
			for (int k=0; k < adj[s].size(); k++) {
				int neighbor = adj[s].get(k);
				if (!visited[neighbor]) {
					visited[neighbor] = true;
					queue.add(neighbor);
				}
			}
		}
	}

	void DFS(int s) {
		boolean[] visited = new boolean[V];
		Stack<Integer> stack = new Stack<>();
		stack.push(s);
		while (!stack.empty()) {
			s = stack.pop();
			if (!visited[s]) {
				System.out.print(s + " ");
				visited[s] = true;
			}
			
			for (int k=0; k < adj[s].size(); k++) {
				int v = adj[s].get(k);
				if (!visited[v])
					stack.push(v);
			}
		}
	}

	/*
	 * Cycle Detection in Directed Graph using DFS TC - O(V+E) if a node is in
	 * recursion stack, its a cycle we will maintain the visited array and recstack
	 * array in this case
	 */
	public boolean hasCycle() {

		boolean[] visited = new boolean[V];
		boolean[] helper = new boolean[V];
		
		for(int i = 0; i < V; i++)
        {   
            if(!visited[i]) {
                if (dfs_cyclicDetection(i , visited , helper)) {
                	return true;
                }
            }
        }
		return false;

	}
	
	
	public boolean dfs_cyclicDetection(int i,  boolean visited[],  boolean helper[]) {
        
        visited[i] = true;
        helper[i] = true; 
        LinkedList<Integer> neighbour = adj[i];
        
        for(int k = 0; k < neighbour.size(); k++)
        {
            int curr = neighbour.get(k);
            if(helper[curr]) return true;
            
            if(!visited[curr])
            {
            	return dfs_cyclicDetection(curr,visited,helper);
            }
        }
        helper[i] = false;  
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

		System.out.println("Following is Breadth First Traversal " + "(starting from vertex 0)");
		g.BFS(0);
		System.out.println("");
		System.out.println("Following is Depth First Traversal " + "(starting from vertex 0)");
		g.DFS(0);
	}
}