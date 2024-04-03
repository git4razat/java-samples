package sample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class UnionFind {
	public int[] parent;
	public int[] rank;

	public UnionFind(int n) {
		parent = new int[n];
		rank = new int[n];
		Arrays.fill(parent, -1); // water
	}
	
	public int find(int u) {
		if (parent[u] != u) {
			parent[u] = find(parent[u]);
		}
		return parent[u]; 
	}

	public void unionByRank(int u, int v) {
		int i = find(u);
		int j = find(v);
		if (i == j)
			return;
		if (rank[i] < rank[j]) {
			parent[i] = j;
		} else if (rank[i] > rank[j]) {
			parent[j] = i;
		} else {
			parent[i] = j;
			++rank[j];
		}
	}
}

// O(k log mn) - k is length of positions array
// union and rank operations are log(mn)

public class NumOfIslandsUnionFind {
	
	public List<Integer> numIslands2(int m, int n, int[][] positions) {
		
		int[][] dirs = { 
				{ 0, 1 },
				{ 1, 0 },
				{ 0, -1 },
				{ -1, 0 } 
		};
		
		List<Integer> result = new ArrayList<>();
		boolean[][] visited = new boolean[m][n];
		UnionFind uf = new UnionFind(m * n);
		int count = 0;

		for (int[] position : positions) {
			int i = position[0];
			int j = position[1];
			// if visited , do nothing but add current count to result list
			if (visited[i][j]) {
				result.add(count);
				continue;
			}
			
			visited[i][j] = true;
			
			int id = getId(i, j, n);
			
			uf.parent[id] = id;
			
			++count;
			
			for (int[] dir : dirs) {
				int x = i + dir[0];
				int y = j + dir[1];
				if (x < 0 || x == m || y < 0 || y == n)
					continue;
				
				int neighborId = getId(x, y, n);
				if (uf.parent[neighborId] == -1) // Water
					continue;
				
				int currentParent = uf.find(id);
				int neighborParent = uf.find(neighborId);
				
				if (currentParent != neighborParent) {
					uf.unionByRank(currentParent, neighborParent);
					--count;
				}
			}
			result.add(count);
		}
		return result;
	}

	private int getId(int row, int col, int numOfCol) {
		return row * numOfCol + col;
	}
	
	public static void main(String[] args) {
		int[][] a = new int[][] { 
			{ 0, 0 }, 
			{ 0, 1 }, 
			{ 4, 4 }, 
			{ 2, 1 },
			{ 3, 4 }
		};
		NumOfIslandsUnionFind obj = new NumOfIslandsUnionFind();
		System.out.println(obj.numIslands2(5,5, a));
	}

}
