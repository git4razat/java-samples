package sample;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class GraphOfStrings {

	Map<String, List<String>> adjVertices;

	public GraphOfStrings() {
		// TODO Auto-generated constructor stub
		adjVertices = new HashMap<String, List<String>>();
	}

	public void addVertex(String label) {
		adjVertices.putIfAbsent(label, new ArrayList<>());
	}

	public void removeVertex(String label) {
		// Vertex v = new Vertex(label);
		adjVertices.values().stream().forEach(e -> e.remove(label));
		adjVertices.remove(label);
	}

	public void addEdge(String label1, String label2) {
		adjVertices.get(label1).add(label2);
		adjVertices.get(label2).add(label1);
	}

	public void removeEdge(String label1, String label2) {

		List<String> eV1 = adjVertices.get(label1);
		List<String> eV2 = adjVertices.get(label2);
		if (eV1 != null)
			eV1.remove(label2);
		if (eV2 != null)
			eV2.remove(label1);
	}

	public List<String> getAdjVertices(String label) {
		return adjVertices.get(label);
	}

	public void printGraph() {

		Set<String> vertexSet = adjVertices.keySet();
		Iterator<String> itr = vertexSet.iterator();
		// System.out.println("Printing Vertex List");
		// System.out.println("Printing Vertex List::"+ adjVertices);
		while (itr.hasNext()) {
			String label = itr.next();
			System.out.print("\n Printing edges for :: " + label + "  ->  ");
			List<String> edges = adjVertices.get(label);
			for (int i = 0; i < edges.size(); i++) {
				System.out.print("  " + edges.get(i));
			}

		}
	}

	public static void main(String[] args) {
		GraphOfStrings graph = new GraphOfStrings();
		graph.addVertex("Bob");
		graph.addVertex("Alice");
		graph.addVertex("Mark");
		graph.addVertex("Rob");
		graph.addVertex("Maria");
		graph.addEdge("Bob", "Alice");
		graph.addEdge("Bob", "Rob");
		graph.addEdge("Alice", "Mark");
		graph.addEdge("Rob", "Mark");
		graph.addEdge("Alice", "Maria");
		graph.addEdge("Rob", "Maria");
		graph.printGraph();

		Set<String> dfsLabels = graph.depthFirstTraversal(graph, "Bob");
		System.out.println("\n\n======= DFS ======\n");
		System.out.println(dfsLabels);

		Set<String> bfsLabels = graph.breadthFirstTraversal(graph, "Bob");
		System.out.println("\n\n======= BFS ======\n");
		System.out.println(bfsLabels);
	}

	Set<String> depthFirstTraversal(GraphOfStrings graph, String root) {
		Set<String> visited = new LinkedHashSet<String>();
		Stack<String> stack = new Stack<String>();
		stack.push(root);
		while (!stack.isEmpty()) {
			String label = stack.pop();
			if (!visited.contains(label)) {
				visited.add(label);
				List<String> adjVertices = graph.getAdjVertices(label);
				for (int i = 0; i < adjVertices.size(); i++) {
					stack.push(adjVertices.get(i));
				}
			}
		}
		return visited;
	}

	Set<String> breadthFirstTraversal(GraphOfStrings graph, String root) {
		Set<String> visited = new LinkedHashSet<String>();
		Queue<String> queue = new LinkedList<String>();
		queue.add(root);
		visited.add(root);
		while (!queue.isEmpty()) {
			String vertex = queue.poll();
			List<String> adjVertices = graph.getAdjVertices(vertex);
			for (int i = 0; i < adjVertices.size(); i++) {
				if (!visited.contains(adjVertices.get(i))) {
					visited.add(adjVertices.get(i));
					queue.add(adjVertices.get(i));
				}
			}
		}
		return visited;
	}

	// directed graph cycle detection
	private boolean isCyclic() {

		// Mark all the vertices as not visited and
		// not part of recursion stack
		HashSet<String> visited = new LinkedHashSet<String>();
		Stack<String> recStack = new Stack<String>();

		// Call the recursive helper function to
		// detect cycle in different DFS trees
		Set<String> keySet = adjVertices.keySet();
		for (String key : keySet) {
			if (isCyclicUtil(key, visited, recStack))
				return true;
		}
		// for (int i = 0; i < keys.size(); i++)

		return false;
	}

	private boolean isCyclicUtil(String key, HashSet<String> visited, Stack<String> recStack) {

		// Mark the current node as visited and 
		// part of recursion stack 
		
		if (recStack.contains(key))
			return true;

		if (visited.contains(key))
			return false;

		visited.add(key);
		recStack.add(key);

		List<String> children = adjVertices.get(key);

		for (String c : children)
			if (isCyclicUtil(c, visited, recStack))
				return true;

		recStack.remove(key);
		return false;
	}

}
