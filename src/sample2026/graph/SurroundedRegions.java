package sample2026.graph;

import java.util.LinkedList;
import java.util.Queue;

public class SurroundedRegions {

    private class Node {
        int row;
        int col;
        Node(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    int[][] directions = new int [][]{{-1,0}, {0,-1}, {0,1}, {1,0}};

    private boolean isValid(int row, int col, int rows, int cols) {
        if (row >= 0 && row < rows && col >= 0 && col < cols)
            return true;
        else
            return false;
    }

    public void solve(int[][] board) {
        int rows = board.length;
        int cols = board[0].length;

        Queue<Node> queue = new LinkedList<>();
        for (int i = 0 ; i < rows; i++) {
            for (int j=0; j < cols; j++) {
                if (i ==0 || i == rows -1 || j == 0 || j == cols - 1) {
                    if (board[i][j] == 'O') {
                        queue.add(new Node(i, j));
                        board[i][j] = 'V';
                    }
                }
            }
        }

        while(!queue.isEmpty()) {
            Node node = queue.poll();

            for(int[] direction: directions) {
                int newRow = node.row + direction[0];
                int newCol = node.col + direction[1];
                if (isValid(newRow, newCol, rows, cols) && board[newRow][newCol] == 'O') {
                    queue.add(new Node(newRow, newCol));
                    board[newRow][newCol] = 'V';
                }
            }
        }

        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                if(board[i][j] == 'V') {
                    board[i][j] = 'O';
                } else {
                    board[i][j] = 'X';
                }
            }
        }
    }
}
