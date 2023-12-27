import java.util.Stack;

public class Aufgabe31 {

    int[][] sudoku = new int[9][9];

    public static void main(String[] args) {

        int[][] sudoku = {
                // x
                { 5, 3, 0, 0, 7, 0, 0, 0, 0 },
                { 6, 0, 0, 1, 9, 5, 0, 0, 0 },
                { 0, 9, 8, 0, 0, 0, 0, 6, 0 },
                { 8, 0, 0, 0, 6, 0, 0, 0, 3 }, // y
                { 4, 0, 0, 8, 0, 3, 0, 0, 1 },
                { 7, 0, 0, 0, 2, 0, 0, 0, 6 },
                { 0, 6, 0, 0, 0, 0, 2, 8, 0 },
                { 0, 0, 0, 4, 1, 9, 0, 0, 5 },
                { 0, 0, 0, 0, 8, 0, 0, 7, 9 } };

    }

    public static void solve(int[][] sudoku, int x, int y) {

        if (sudoku[y][x] != 0) {
            if (sudoku[y].length == x) {
                solve(sudoku, 0, y + 1);
            } else {
                solve(sudoku, x + 1, y);
            }
        }

        for (int i = 1; i < sudoku.length + 1; i++) {
            sudoku[y][x] = i;

            if (testCol(sudoku, x, y) && testCol(sudoku, x, y) && testQuad(sudoku, x, y)) {
                if (sudoku[y].length == x) {
                    solve(sudoku, 0, y + 1);
                } else {
                    solve(sudoku, x + 1, y);
                }
            }
        }
    }

    public static boolean testRow(int[][] sudoku, int x, int y) {

        int newVal = sudoku[y][x];

        for (int i = 0; i <= x; i++) {
            if (sudoku[y][x] == sudoku[y][i]) {
                return false;
            }
        }

        return true;
    }

    public static boolean testCol(int[][] sudoku, int x, int y) {

        int newVal = sudoku[y][x];

        for (int i = 0; i <= y; i++) {
            if (sudoku[y][x] == sudoku[i][x]) {
                return false;
            }
        }

        return true;
    }

    public static boolean testQuad(int[][] sudoku, int x, int y) {

        int newVal = sudoku[y][x];

        return true;
    }
}
