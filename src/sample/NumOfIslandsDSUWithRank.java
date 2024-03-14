package sample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Don't use this pls use NumOfISlandsUnionFind

public class NumOfIslandsDSUWithRank {
	
	
	static class UnionFind {
		int[] parents;
		int[] ranks;
		int count=0;
		int m,n;
		
		
		int[][] directions = {{0,1},{0,-1}, {1,0},{-1,0}};
		
		
		UnionFind(int mGrid, int nGrid) {
			m = mGrid;
			n = nGrid;
			parents = new int[m*n];
			ranks = new int[m*n];
			
			for (int i =0; i < m; i++) {
				parents[i] = -1;
			}
		}
		
		public int find(int positionIndex) {
			//path compression
			if (parents[positionIndex] != positionIndex) {
				positionIndex = find(parents[positionIndex]);
			}
			return positionIndex;  
		}
		
		public void union(int id1, int id2) {
			// union with rank
			int rootIndex1 = find(id1);
			int rootIndex2 = find(id2);
			
			if (rootIndex1 != rootIndex2) {
				if (ranks[rootIndex1] > ranks[rootIndex2]) {
					parents[rootIndex2] = rootIndex1;
				} else if (ranks[rootIndex2] > ranks[rootIndex1]) {
					parents[rootIndex1] = rootIndex2;
				} else {
					parents[rootIndex2] = rootIndex1;
					ranks[rootIndex1]++;
				}
				count--;
			}
		}
		
		public int place(int[] position) {
			System.out.println("current count :" + count);
			int i = position[0];
			int j = position[1];
			
			if (parents[i * n + j] == -1) {
				count++;
				parents[i * n + j] = i * n + j;
			}
			for (int[] direction: directions) {
				int i2 = i + direction[0];
				int j2 = j + direction[1];
				if (i2 < m && i2 >= 0 && j2 < n && j2 >=0 && parents[i2 * n + j2] != -1) {
					union(i * n + j, i2 * n + j2);
				} 
			}
			return count;
		}
		
		
	}
	
	public List<Integer> numIslands2(int m , int n, int positions[][]) {
		UnionFind uf = new UnionFind(m, n);
		int p = positions.length;
		List<Integer> result = new ArrayList<Integer>();
		for (int x = 0; x < p; x++) {
			result.add(x, uf.place(positions[x]));
		}
		return result;
	}
	
	public static void main(String[] args) {
		int[][] a = new int[][] { 
			{ 0, 0 }, 
			{ 0, 1 }, 
			{ 4, 4 }, 
			{ 2, 1 },
			{ 3, 4 } 
		};
		
		
		
		NumOfIslandsDSUWithRank obj = new NumOfIslandsDSUWithRank();
		System.out.println("Number of Islands is: " + obj.numIslands2(5,5,new int[5][5]));
		
		//UnionFind uf = new UnionFind(2, 2);
		//int p = a.length;
		//System.out.println("positions::" + a.length);
		
		/*List<Integer> result = new ArrayList<Integer>();
		for (int x = 0; x < p; x++) {
			System.out.println("positions[x]::" + ));
			result.add(x, uf.place(positions[x]));
		}*/
		
		//List<Integer> result = new ArrayList<Integer>();
		//System.out.println(uf.place(new int[]{0,0}));
		//System.out.println(uf.place(new int[]{1,1}));
	}

}