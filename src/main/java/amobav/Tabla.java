package amobav;

import java.util.ArrayList;
import java.util.List;

/**
 * A Tabla osztály egy amőba/játék tábla megvalósítása.
 * A tábla négyzethálóból áll, amelynek minden mezője lehet üres,
 * vagy tartalmazhat egy játékos szimbólumát (pl. X vagy O).
 * A tábla felelős:
 * - a mezők tárolásáért,
 * - a mezők lekérdezéséért és módosításáért,
 * - a lépések érvényességének ellenőrzéséért,
 * - 5 azonos szimbólum egymás melletti sorozatának ellenőrzéséért.
 */
public final class Tabla {

    /** A tábla sorainak száma. */
    private final int rows;

    /** A tábla oszlopainak száma. */
    private final int cols;

    /** A tábla mezői. */
    private final char[][] board;

    /** A nyeréshez szükséges egymás melletti jelek száma. */
    private static final int WIN_LENGTH = 5;

    /**
     * Létrehoz egy új tábla példányt a megadott méretekkel.
     * @param numRows sorok száma
     * @param numCols oszlopok száma
     */
    public Tabla(final int numRows, final int numCols) {
        this.rows = numRows;
        this.cols = numCols;
        this.board = new char[rows][cols];
        initialize();
    }

    /**
     * Ellenőrzi, hogy a pozíció a tábla határain belül van-e.
     * @param row sor index
     * @param col oszlop index
     * @return true, ha érvényes pozíció
     */
    public boolean isValid(final int row, final int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    /**
     * Visszaadja a tábla adott mezőjének értékét.
     * @param row sor index
     * @param col oszlop index
     * @return a mező tartalma ('X', 'O' vagy ' ')
     */
    public char getCell(final int row, final int col) {
        return board[row][col];
    }

    /**
     * Ellenőrzi, hogy a megadott mező üres-e.
     * @param row sor index
     * @param col oszlop index
     * @return true, ha a mező üres
     */
    public boolean isEmpty(final int row, final int col) {
        return board[row][col] == ' ';
    }

    /**
     * Lerakja a játékos szimbólumát a megadott mezőre.
     * @param row sor index
     * @param col oszlop index
     * @param symbol a játékos szimbóluma
     * @return true, ha sikerült lerakni, false ha érvénytelen
     */
    public boolean placeMark(final int row, final int col, final char symbol) {
        if (!isValid(row, col) || !isEmpty(row, col)) {
            return false;
        }
        board[row][col] = symbol;
        return true;
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

    /**
     * Visszaadja a tábla összes szabad mezőjét.
     * @return lista a lehetséges lépésekből
     */
    public List<Move> getAvailableMoves() {
        List<Move> moves = new ArrayList<>();
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
     * Kiírja a táblát konzolra, fejléc oszlopokkal és sorszámozott sorokkal.
     */
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
     * Ellenőrzi, hogy a játékos nyert-e (5 azonos jel egymás mellett).
     * @param symbol a játékos szimbóluma
     * @return true, ha nyert
     */
    public boolean checkWin(final char symbol) {
        // vízszintes
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c <= cols - WIN_LENGTH; c++) {
                if (checkLine(symbol, r, c, 0, 1)) {
                    return true;
                }
            }
        }

        // függőleges
        for (int r = 0; r <= rows - WIN_LENGTH; r++) {
            for (int c = 0; c < cols; c++) {
                if (checkLine(symbol, r, c, 1, 0)) {
                    return true;
                }
            }
        }

        // főátló
        for (int r = 0; r <= rows - WIN_LENGTH; r++) {
            for (int c = 0; c <= cols - WIN_LENGTH; c++) {
                if (checkLine(symbol, r, c, 1, 1)) {
                    return true;
                }
            }
        }

        // mellékátló
        for (int r = 0; r <= rows - WIN_LENGTH; r++) {
            for (int c = WIN_LENGTH - 1; c < cols; c++) {
                if (checkLine(symbol, r, c, 1, -1)) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Segédfüggvény, amely ellenőrzi, hogy az adott kezdőponttól
     * adott irányban 5 azonos szimbólum van-e.
     * @param symbol a játékos szimbóluma
     * @param row kezdő sor
     * @param col kezdő oszlop
     * @param dr sorirány
     * @param dc oszlopirány
     * @return true, ha 5 egymás melletti szimbólum van
     */
    private boolean checkLine(final char symbol, final int row, final int col,
                              final int dr, final int dc) {
        for (int i = 0; i < WIN_LENGTH; i++) {
            if (board[row + dr * i][col + dc * i] != symbol) {
                return false;
            }
        }
        return true;
    }

    /**
     * Visszaadja a tábla sorainak számát.
     * @return sorok száma
     */
    public int getRows() {
        return rows;
    }

    /**
     * Visszaadja a tábla oszlopainak számát.
     * @return oszlopok száma
     */
    public int getCols() {
        return cols;
    }
}
