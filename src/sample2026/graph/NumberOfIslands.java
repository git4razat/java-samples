package sample2026.graph;

public class NumberOfIslands {

    int[][] directions = new int[][] {{-1,0}, {1,0}, {0,-1}, {0,1}};

    private void dfs(int row, int col, char[][] matrix) {
        if (row < 0 || row >= matrix.length ||
                col < 0 || col >= matrix[0].length || matrix[row][col] != '1') {
            return;
        }
        matrix[row][col] = '2';
        for (int[] direction: directions) {
            int newRow = row + direction[0];
            int newCol = col + direction[1];
            dfs(newRow, newCol, matrix);
        }
    }

    public int numOfIslands(char[][] matrix) {
        int count = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '1') {
                    dfs(i, j, matrix);
                    count++;
                }
            }
        }
        return count;
    }
}
