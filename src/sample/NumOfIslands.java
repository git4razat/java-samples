package sample;

public class NumOfIslands {
	
	// DFS
	// Each element is visited once only. So time is O(m*n).
	
	// A utility function to do DFS for a 2D boolean matrix. It only considers
    // the 8 neighbours as adjacent vertices
	// o(m*n) - time & space...stack is involved due to recursion...
	
    static void DFS(int[][] M, int i, int j, int ROW, int COL)
    {
 
        // Base condition
        // if i less than 0 or j less than 0 or i greater
        // than ROW-1 or j greater than COL-  or if M[i][j]
        // != 1 then we will simply return
        if (i < 0 || j < 0 || i > (ROW - 1) || j > (COL - 1) || M[i][j] != 1) {
            return;
        }
 
        if (M[i][j] == 1) {
            M[i][j] = 2;
            DFS(M, i + 1, j, ROW, COL); // right side traversal
            DFS(M, i - 1, j, ROW, COL); // left side traversal
            DFS(M, i, j + 1, ROW, COL); // upward side traversal
            DFS(M, i, j - 1, ROW, COL); // downward side traversal
            // if diagonals are also considered
            //DFS(M, i + 1, j + 1, ROW, COL); // upward-right side traversal
            //DFS(M, i - 1, j - 1, ROW, COL); // downward-left side traversal
            //DFS(M, i + 1, j - 1, ROW, COL); // downward-right side traversal
            //DFS(M, i - 1, j + 1, ROW, COL); // upward-left side traversal
        }
    }
 
    static int countIslands(int[][] M)
    {
        int ROW = M.length;
        int COL = M[0].length;
        int count = 0;
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (M[i][j] == 1) {
                    count++;
                    DFS(M, i, j, ROW, COL); // traversal starts from current cell
                }
            }
        }
        return count;
    }
	
	public static void main(String[] args) {
		/*int[][] matrix = {
				{1, 1, 0, 0, 0},
                {0, 1, 0, 0, 1},
                {1, 0, 0, 1, 1},
                {0, 0, 0, 0, 0},
                {1, 0, 1, 0, 0}
                };*/
		// 5
		/*int[][] matrix = {
				{1, 1, 1, 1, 0},
				{1, 1, 0, 1, 0},
				{1, 1, 0, 0, 0},
				{0, 0, 0, 0, 0}
		};*/
		//1
		int[][] matrix = {
				{1, 1, 0, 0, 0},
				{1, 1, 0, 0, 0},
				{0, 0, 1, 0, 0},
				{0, 0, 0, 1, 1}
		};
		//3
		NumOfIslands obj = new NumOfIslands();
		System.out.println(">>" + obj.countIslands(matrix));
		
	}

}
