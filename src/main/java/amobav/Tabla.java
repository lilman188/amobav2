package amobav;

import java.util.ArrayList;
import java.util.List;

/**
 * A {@code Tabla} osztály egy amőba/játék tábla megvalósítása.
 * A tábla négyzethálóból áll, amelynek minden mezője lehet üres,
 * vagy tartalmazhat egy játékos szimbólumát (pl. X vagy O).
 * A tábla felelős:
 * - a mezők tárolásáért,
 * - a mezők lekérdezéséért és módosításáért,
 * - egy lépés szabályos-e (mező üres-e),
 * - 5 azonos szimbólum egymás melletti sorozatának ellenőrzéséért.
 */
public class Tabla {

    /** A tábla sorainak száma. */
    private final int rows;

    /** A tábla oszlopainak száma. */
    private final int cols;

    /** A tábla mezői. */
    private final char[][] board;

    /**
     * Létrehoz egy új tábla példányt a megadott méretekkel.
     *
     * @param rows sorok száma.
     * @param cols oszlopok száma.
     */
    public Tabla(final int rows, final int cols) {
        this.rows = rows;
        this.cols = cols;
        this.board = new char[rows][cols];
        initialize();
    }

    /**
     * Ellenőrzi, hogy a pozíció érvényes-e.
     *
     * @param row sor index.
     * @param col oszlop index.
     * @return true, ha érvényes, különben false.
     */
    public boolean isValid(final int row, final int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    /**
     * Visszaadja a tábla adott cellájának értékét.
     *
     * @param row sor index.
     * @param col oszlop index.
     * @return a cella értéke.
     */
    public char getCell(final int row, final int col) {
        return board[row][col];
    }

    /**
     * Visszaadja az összes üres mezőt.
     *
     * @return az elérhető lépések listája.
     */
    public List<Move> getAvailableMoves() {
        final List<Move> moves = new ArrayList<>();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (isEmpty(r, c)) {
                    moves.add(new Move(r, c));
                }
            }
        }
        return moves;
    }

    /**
     * Inicializálja a tábla mezőit üres karakterekkel (' ').
     */
    public void initialize() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = ' ';
            }
        }
    }

    /** @return a tábla sorainak száma. */
    public int getRows() {
        return rows;
    }

    /** @return a tábla oszlopainak száma. */
    public int getCols() {
        return cols;
    }

    /**
     * Ellenőrzi, hogy a megadott pozíció üres-e.
     *
     * @param row sor index.
     * @param col oszlop index.
     * @return true, ha üres, különben false.
     */
    public boolean isEmpty(final int row, final int col) {
        return board[row][col] == ' ';
    }

    /**
     * Lerakja a játékos szimbólumát a megadott pozícióra.
     *
     * @param row sor index.
     * @param col oszlop index.
     * @param symbol a játékos szimbóluma.
     * @return true, ha sikerült lerakni, különben false.
     */
    public boolean placeMark(final int row, final int col, final char symbol) {
        if (!isValid(row, col) || !isEmpty(row, col)) {
            return false;
        }
        board[row][col] = symbol;
        return true;
    }

    /** Kiírja a táblát konzolra. */
    public void print() {
        System.out.print("   ");
        for (int col = 0; col < cols; col++) {
            System.out.print((char) ('A' + col) + " ");
        }
        System.out.println();

        for (int row = 0; row < rows; row++) {
            System.out.printf("%2d ", row + 1);
            for (int col = 0; col < cols; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Ellenőrzi, hogy a megadott játékos létrehozott-e
     * 5 azonos jelet egymás mellett.
     *
     * @param symbol a játékos szimbóluma.
     * @return true, ha nyert, különben false.
     */
    public boolean checkWin(final char symbol) {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c <= cols - 5; c++) {
                if (checkLine(symbol, r, c, 0, 1)) {
                    return true;
                }
            }
        }
        for (int r = 0; r <= rows - 5; r++) {
            for (int c = 0; c < cols; c++) {
                if (checkLine(symbol, r, c, 1, 0)) {
                    return true;
                }
            }
        }
        for (int r = 0; r <= rows - 5; r++) {
            for (int c = 0; c <= cols - 5; c++) {
                if (checkLine(symbol, r, c, 1, 1)) {
                    return true;
                }
            }
        }
        for (int r = 0; r <= rows - 5; r++) {
            for (int c = 4; c < cols; c++) {
                if (checkLine(symbol, r, c, 1, -1)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkLine(final char symbol, final int row, final int col, final int dr, final int dc) {
        for (int i = 0; i < 5; i++) {
            if (board[row + dr * i][col + dc * i] != symbol) {
                return false;
            }
        }
        return true;
    }
}
