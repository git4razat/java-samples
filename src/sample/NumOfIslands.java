package sample;

public class NumOfIslands {
	
	// DFS
	// Each element is visited once only. So time is O(m*n).
	
	// A utility function to do DFS for a 2D boolean matrix. It only considers
    // the 8 neighbours as adjacent vertices
	// o(mn) - time & space...stack is involved due to recursion...
	// O(mn) - space  -- we may need to save the whole grid in memory if all cells are 1
	public int numIslands(char[][] grid) {
	    int result = 0;

	    for (int i = 0; i < grid.length; ++i)
	      for (int j = 0; j < grid[0].length; ++j)
	        if (grid[i][j] == '1') {
	          dfs(grid, i, j);
	          ++result;
	        }
	    return result;
	  }

	  private void dfs(char[][] grid, int i, int j) {
	    if (i < 0 || i == grid.length || j < 0 || j == grid[0].length)
	      return;
	    if (grid[i][j] != '1')
	      return;

	    grid[i][j] = '2'; // Mark '2' as visited.
	    dfs(grid, i + 1, j);
	    dfs(grid, i - 1, j);
	    dfs(grid, i, j + 1);
	    dfs(grid, i, j - 1);
	  }
	
	public static void main(String[] args) {
		char[][] matrix = {
				{1, 1, 0, 0, 0},
				{1, 1, 0, 0, 0},
				{0, 0, 1, 0, 0},
				{0, 0, 0, 1, 1}
		};
		//3
		NumOfIslands obj = new NumOfIslands();
		System.out.println(">>" + obj.numIslands(matrix));
		
	}

}
