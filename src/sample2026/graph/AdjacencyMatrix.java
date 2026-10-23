package sample2026.graph;

public class AdjacencyMatrix {

    public void printGraph(int[][] grid, int n) {
        for (int i = 0; i < n; i++) {
            System.out.print(i + " : ");
            for (int j = 0; j < n ; j++) {
                if (grid[i][j] == 1) {
                    System.out.print(j + " ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] grid = new int[][] {
                {0, 1, 1, 0, 0, 0},
                {1, 0, 1, 1, 1, 0},
                {1, 1, 0, 0, 0, 1},
                {0, 1, 0, 0, 1, 1},
                {0, 1, 0, 1, 0, 0},
                {0, 0, 1, 1, 0, 0}

        };
        int num_vertices = 6;
        AdjacencyMatrix matrix = new AdjacencyMatrix();
        matrix.printGraph(grid, num_vertices);
    }
}
