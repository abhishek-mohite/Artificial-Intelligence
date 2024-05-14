import java.util.*;

public class NQueens {

    // Function to solve the N-Queens problem using backtracking
    public static List<List<String>> solveNQueensBacktracking(int n) {
        List<List<String>> solutions = new ArrayList<>();
        char[][] board = new char[n][n];
        for (char[] row : board) {
            Arrays.fill(row, '.');
        }
        backtrack(solutions, board, 0, n);
        return solutions;
    }

    private static void backtrack(List<List<String>> solutions, char[][] board, int col, int n) {
        if (col == n) {
            solutions.add(constructSolution(board));
            return;
        }
        for (int row = 0; row < n; row++) {
            if (isValid(board, row, col, n)) {
                board[row][col] = 'Q';
                backtrack(solutions, board, col + 1, n);
                board[row][col] = '.';
            }
        }
    }

    private static boolean isValid(char[][] board, int row, int col, int n) {
        for (int i = 0; i < col; i++) {
            if (board[row][i] == 'Q') return false;
        }
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') return false;
        }
        for (int i = row, j = col; i < n && j >= 0; i++, j--) {
            if (board[i][j] == 'Q') return false;
        }
        return true;
    }

    private static List<String> constructSolution(char[][] board) {
        List<String> solution = new ArrayList<>();
        for (char[] row : board) {
            solution.add(new String(row));
        }
        return solution;
    }

    // Function to solve the N-Queens problem using Branch and Bound
    public static List<List<String>> solveNQueensBranchAndBound(int n) {
        List<List<String>> solutions = new ArrayList<>();
        char[][] board = new char[n][n];
        for (char[] row : board) {
            Arrays.fill(row, '.');
        }
        int[] colCount = new int[n];
        int[] diag1Count = new int[2 * n - 1];
        int[] diag2Count = new int[2 * n - 1];
        branchAndBound(solutions, board, 0, n, colCount, diag1Count, diag2Count);
        return solutions;
    }

    private static void branchAndBound(List<List<String>> solutions, char[][] board, int row, int n,
                                       int[] colCount, int[] diag1Count, int[] diag2Count) {
        if (row == n) {
            solutions.add(constructSolution(board));
            return;
        }
        for (int col = 0; col < n; col++) {
            if (colCount[col] == 0 && diag1Count[row + col] == 0 && diag2Count[row - col + n - 1] == 0) {
                board[row][col] = 'Q';
                colCount[col]++;
                diag1Count[row + col]++;
                diag2Count[row - col + n - 1]++;
                branchAndBound(solutions, board, row + 1, n, colCount, diag1Count, diag2Count);
                board[row][col] = '.';
                colCount[col]--;
                diag1Count[row + col]--;
                diag2Count[row - col + n - 1]--;
            }
        }
    }

    public static void main(String[] args) {
        int n = 4; // Number of queens
        System.out.println("Backtracking solutions:");
        List<List<String>> backtrackingSolutions = solveNQueensBacktracking(n);
        printSolutions(backtrackingSolutions);
        System.out.println("\nBranch and Bound solutions:");
        List<List<String>> branchAndBoundSolutions = solveNQueensBranchAndBound(n);
        printSolutions(branchAndBoundSolutions);
    }

    private static void printSolutions(List<List<String>> solutions) {
        for (List<String> solution : solutions) {
            for (String row : solution) {
                System.out.println(row);
            }
            System.out.println();
        }
        System.out.println("Number of solutions: " + solutions.size());
    }
}

