package sample;

import java.util.ArrayList;
import java.util.List;

class Solution {
    class UnionFind {
        int count; // # of connected components
        int[] parent;
        int[] rank;

        public UnionFind(char[][] grid) { // for problem 200
            count = 0;
            int m = grid.length;
            int n = grid[0].length;
            parent = new int[m * n];
            rank = new int[m * n];
            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (grid[i][j] == '1') {
                        parent[i * n + j] = i * n + j;
                        ++count;
                    }
                    rank[i * n + j] = 0;
                }
            }
        }

        public UnionFind(int N) { // for problem 305 and others
            count = 0;
            parent = new int[N];
            rank = new int[N];
            for (int i = 0; i < N; ++i) {
                parent[i] = -1;
                rank[i] = 0;
            }
        }

        public boolean isValid(int i) { // for problem 305
            return parent[i] >= 0;
        }

        public void setParent(int i) {
            parent[i] = i;
            ++count;
        }

        public int find(int i) { // path compression
            if (parent[i] != i) parent[i] = find(parent[i]);
            return parent[i];
        }

        public void union(int x, int y) { // union with rank
            int rootx = find(x);
            int rooty = find(y);
            if (rootx != rooty) {
                if (rank[rootx] > rank[rooty]) {
                    parent[rooty] = rootx;
                } else if (rank[rootx] < rank[rooty]) {
                    parent[rootx] = rooty;
                } else {
                    parent[rooty] = rootx; rank[rootx] += 1;
                }
                --count;
            }
        }

        public int getCount() {
            return count;
        }
    }

    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> ans = new ArrayList<>();
        UnionFind uf = new UnionFind(m * n);

        int[] dr = {0, 1, 0, -1};
        int[] dc = {1, 0, -1, 0};

        for (int[] pos : positions) {
            int r = pos[0], c = pos[1];
            int id = r * n + c;
            uf.setParent(id);

            for (int k = 0; k < 4; k++) {
                int nr =  r + dr[k];
                int nc =  c + dc[k];
                int nid = nr * n + nc;

                if (nr >= 0 && nr < m && nc >= 0 && nc < n) {
                    if (uf.isValid(nid)) {
                        uf.union(nid, id);
                    }
                }
            }
            ans.add(uf.getCount());
        }
        return ans;
    }
    
    public static void main(String[] args) {
    	int positions[][] = {
    			{0,0},
    			{0,1},
    			{2,0},
    			{2,0}
    	};
		Solution obj = new Solution();
		System.out.println(obj.numIslands2(3, 3, positions));
	}
}
