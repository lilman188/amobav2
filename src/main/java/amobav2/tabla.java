package hu.egyetem.amobav2;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final char[][] grid;
    private final int rows;
    private final int cols;

    public Board(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new char[rows][cols];
    }

    public void initialize() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = '.';
            }
        }
    }

    public void print() {
        System.out.print("  ");
        for (int j = 0; j < cols; j++) System.out.print((char)('A' + j) + " ");
        System.out.println();
        for (int i = 0; i < rows; i++) {
            System.out.printf("%2d ", i + 1);
            for (int j = 0; j < cols; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    public boolean isEmpty(int row, int col) {
        return grid[row][col] == '.';
    }

    public boolean placeMark(int row, int col, char symbol) {
        if (row < 0 || col < 0 || row >= rows || col >= cols || !isEmpty(row, col))
            return false;
        grid[row][col] = symbol;
        return true;
    }

    public boolean checkWin(char symbol) {
        int count;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] != symbol) continue;

                // jobbra
                if (j + 4 < cols) {
                    count = 0;
                    for (int k = 0; k < 5; k++) if (grid[i][j + k] == symbol) count++;
                    if (count == 5) return true;
                }

                // le
                if (i + 4 < rows) {
                    count = 0;
                    for (int k = 0; k < 5; k++) if (grid[i + k][j] == symbol) count++;
                    if (count == 5) return true;
                }

                // átló (jobb-le)
                if (i + 4 < rows && j + 4 < cols) {
                    count = 0;
                    for (int k = 0; k < 5; k++) if (grid[i + k][j + k] == symbol) count++;
                    if (count == 5) return true;
                }

                // átló (bal-le)
                if (i + 4 < rows && j - 4 >= 0) {
                    count = 0;
                    for (int k = 0; k < 5; k++) if (grid[i + k][j - k] == symbol) count++;
                    if (count == 5) return true;
                }
            }
        }
        return false;
    }

    public List<Move> getAvailableMoves() {
        List<Move> moves = new ArrayList<>();
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                if (isEmpty(i, j)) moves.add(new Move(i, j));
        return moves;
    }

    public int getRows() { return rows; }
    public int getCols() { return cols; }
}
