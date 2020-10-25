package sample;

import java.util.LinkedList;

public class Graph{
	
	private int numV; // number of vertices
	private java.util.LinkedList<Integer> adjList[];
	
	public Graph(int numV) {
		this.numV = numV;
		adjList = new LinkedList[this.numV]; 
        for (int i=0; i< numV; ++i) {
        	adjList[i] = new LinkedList();
        }
	}
	
	void addEdge(int v, int w) {
		//directed graph
		adjList[v].add(w);
		
		//undirected graph needs to include reverse as well
		//adjList[w].add(v);
	}
	
	void printGraph() {
		for(int i = 0; i < adjList.length; i++) {
			System.out.print("\nAdjacency List of vertex: "+ i  + " -> ");
			LinkedList list = adjList[i];
			for(int j = 0; j < list.size(); j++) {
				System.out.print("  " + list.get(j));
			}
		}
	}
	
	// Driver method to 
    public static void main(String args[]) 
    { 
        Graph g = new Graph(4); 
  
        g.addEdge(0, 1); 
        g.addEdge(0, 2); 
        g.addEdge(1, 2); 
        g.addEdge(2, 0); 
        g.addEdge(2, 3); 
        g.addEdge(3, 3);
        g.printGraph();
  
        //System.out.println("Following is Breadth First Traversal "+ 
        //                   "(starting from vertex 2)"); 
  
        //g.BFS(2); 
    } 
	
	
	
	

}
