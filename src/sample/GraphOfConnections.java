package sample;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GraphOfConnections {
	
	Map<Vertex, List<Vertex>> adjVertices;
	
	public GraphOfConnections() {
		// TODO Auto-generated constructor stub
		adjVertices = new HashMap<Vertex, List<Vertex>>();
	}
	
	public void addVertex(String label) {
		adjVertices.putIfAbsent(new Vertex(label), new ArrayList<>());
	}
	
	public void removeVertex(String label) {
		Vertex v = new Vertex(label);
		adjVertices.values().stream().forEach(e -> e.remove(v));
		adjVertices.remove(new Vertex(label));
	}
	
	public void addEdge(String label1, String label2) {
		Vertex v1 = new Vertex(label1);
		Vertex v2 = new Vertex(label2);
		List<Vertex> l1 = adjVertices.get(v1);
		if(l1 != null) {
			l1.add(v2);
		} else {
			l1 = new LinkedList<Vertex>();
			l1.add(v2);
		}
		adjVertices.putIfAbsent(v1, l1);
		
		List<Vertex> l2 = adjVertices.get(v2);
		if(l2 != null) {
			l2.add(v1);
		} else {
			l2 = new LinkedList<Vertex>();
			l2.add(v1);
		}
		adjVertices.putIfAbsent(v2, l2);
	}
	
	public void removeEdge(String label1, String label2) {
		Vertex v1 = new Vertex(label1);
		Vertex v2 = new Vertex(label2);
		List<Vertex> l1 = adjVertices.get(v1);
		if(l1 != null) {
			l1.remove(v2);
		}
		
		List<Vertex> l2 = adjVertices.get(v2);
		if(l2 != null) {
			l2.remove(v1);
		}
	}
	
	public void printGraph() {
		
		Set<Vertex> vertexSet = adjVertices.keySet();
		Iterator<Vertex> itr = vertexSet.iterator();
		//System.out.println("Printing Vertex List");
		System.out.println("Printing Vertex List::"+ adjVertices);
		/*while(itr.hasNext()) {
			Vertex v = itr.next();
			System.out.print("Printing edges for :: " + v.getLabel() + "  ->  \n");
			List<Vertex> edges = adjVertices.get(v);
			for(int i = 0; i < edges.size(); i++) {
				System.out.println("  "+ edges.get(i).getLabel());
			}
			
		}*/		
	}
	
	public static void main(String[] args) {
		GraphOfConnections graph = new GraphOfConnections();
		graph.addVertex("Bob");
		graph.addVertex("John");
		graph.addVertex("Roger");
		graph.addVertex("Peter");
		graph.addVertex("David");
		
		graph.addEdge("Bob", "Peter");
		graph.addEdge("Bob", "Roger");
		graph.addEdge("David", "Peter");
		graph.addEdge("David", "Roger");
		graph.addEdge("John", "Bob");
		graph.addEdge("John", "David");
		
		graph.printGraph();
		
		
	}

}

class Vertex {
	private String label;
	Vertex(String label) {
		this.label = label;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	
}
