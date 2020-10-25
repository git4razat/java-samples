package sample;

import java.util.*;

//Data structure to store graph edges
class Edge {
	int src, dest;

	public Edge(int src, int dest) {
		this.src = src;
		this.dest = dest;
	}
};

//A queue node
class QNode {
	// stores number associated with graph node
	int ver;

	// minDist stores minimum distance of node from starting vertex
	int minDist;

	public QNode(int ver, int minDist) {
		this.ver = ver;
		this.minDist = minDist;
	}
};

//class to represent a graph object
class DGraph {
	// A List of Lists to represent an adjacency list
	List<List<Integer>> adjList = null;

	// Constructor
	DGraph(List<Edge> edges, int N) {
		adjList = new ArrayList<>(N);
		for (int i = 0; i < N; i++) {
			adjList.add(i, new ArrayList<>());
		}

		// add edges to the graph
		for (int i = 0; i < edges.size(); i++) {
			int src = edges.get(i).src;
			int dest = edges.get(i).dest;

			// Please note that graph is directed
			adjList.get(src).add(dest);
		}
	}
}

class SnakeLadder {
	// Perform BFS on graph g starting from given source vertex
	public static void BFS(DGraph g, int source, int N) {
		// create a queue used to do BFS
		Queue<QNode> q = new ArrayDeque<>();

		// stores vertex is discovered or not
		boolean[] visited = new boolean[N + 1];

		// mark source vertex as discovered
		visited[source] = true;

		// assign minimum distance of source vertex as 0 and
		// push it into the queue
		QNode node = new QNode(source, 0);
		q.add(node);

		// run till queue is not empty
		while (!q.isEmpty()) {
			// pop front node from queue
			node = q.poll();

			// Stop BFS if we have reached last node
			if (node.ver == N) {
				System.out.println(node.minDist);
				break;
			}

			// do for every adjacent node of current node
			//for (int u : g.adjList.get(node.ver)) {
			List<Integer> adjList = g.adjList.get(node.ver);
			for(int i = 0; i < adjList.size(); i++) {
				if (!visited[adjList.get(i)]) {
					// mark it discovered & push it into queue
					visited[adjList.get(i)] = true;

					// assign minimum distance of current node
					// as minimum distance of parent node + 1
					// 1 -> 2,3,4,14,6,7
					// 2 -> 3,4,14,6,7,8
					// QNode (1,0)
					QNode n = new QNode(adjList.get(i), node.minDist + 1);
					q.add(n);
				}
			}
		}
	}

	public static void findSolution(Map<Integer, Integer> ladder, Map<Integer, Integer> snake) {
		// Number of vertices in the graph
		int N = 10 * 10;

		// find all edges involved and store them in a list
		List<Edge> edges = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 1; j <= 6 && i + j <= N; j++) {
				int src = i;

				// update destination if there is any ladder
				// or snake from current position.
				int dest;

				int _ladder = (ladder.get(i + j) != null) ? ladder.get(i + j) : 0;

				int _snake = (snake.get(i + j) != null) ? snake.get(i + j) : 0;

				if (_ladder != 0 || _snake != 0)
					dest = _ladder + _snake;
				else
					dest = i + j;

				// add edge from src to dest
				edges.add(new Edge(src, dest));
			}
		}

		// construct directed graph
		DGraph g = new DGraph(edges, N);

		// Find Shortest path between 1 and 100 using BFS
		BFS(g, 0, N);
	}

	public static void main(String[] args) {
		
		//int[][] test = new int[10][10];
		//System.out.println("test length :: " + test.length);
		
		
		// snakes and ladders are represented using a map.
		Map<Integer, Integer> ladder = new HashMap();
		Map<Integer, Integer> snake = new HashMap();

		// insert ladders into the map
		ladder.put(1, 38);
		ladder.put(4, 14);
		ladder.put(9, 31);
		ladder.put(21, 42);
		ladder.put(28, 84);
		ladder.put(51, 67);
		ladder.put(72, 91);
		ladder.put(80, 99);

		// insert snakes into the map
		snake.put(17, 7);
		snake.put(54, 34);
		snake.put(62, 19);
		snake.put(64, 60);
		snake.put(87, 36);
		snake.put(93, 73);
		snake.put(95, 75);
		snake.put(98, 79);

		findSolution(ladder, snake);
	}
}
