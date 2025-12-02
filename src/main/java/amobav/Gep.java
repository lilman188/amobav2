package amobav;

import java.util.List;
import java.util.Random;

/**
 * Egyszerű gépi játékos.
 * Védekező logikát alkalmaz: blokkolja az emberi játékos közelgő sorozatát,
 * különben véletlenszerűen lép.
 */
public class Gep extends Jatekos {
    /** A sorozat hossza, amely szükséges a győzelemhez. */
    private static final int WIN_LENGTH = 5;

    /** Küszöbérték a blokkolási logika számításához. */
    private static final int BLOCK_THRESHOLD = 3;

    /** Véletlenszám-generátor a gép lépéseinek
     * véletlenszerű kiválasztásához. */
    private final Random random = new Random();

    /**
     * Létrehoz egy új gépi játékost a megadott szimbólummal.
     *
     * @param playerSymbol a játékos szimbóluma
     */
    public Gep(final char playerSymbol) {
        super(playerSymbol);
    }

    /**
     * Visszaadja a gép lépését.
     * Először megpróbálja blokkolni az ellenfél sorozatát,
     * különben véletlenszerű lépést választ.
     *
     * @param board a játék tábla
     * @return a kiválasztott lépés
     */
    @Override
    public Move getMove(final Tabla board) {
        Move block = findBlockingMove(board, 'X');
        if (block != null) {
            System.out.println("Gép blokkol: " + formatMove(block));
            return block;
        }

        List<Move> possibleMoves = board.getAvailableMoves();
        Move move = possibleMoves.get(random.nextInt(possibleMoves.size()));
        System.out.println("Gép véletlen lépése: " + formatMove(move));
        return move;
    }

    /**
     * Megkeresi a blokk lépést, ha az ellenfél közelít a győzelemhez.
     *
     * @param board a játék tábla
     * @param enemySymbol az ellenfél szimbóluma
     * @return a blokk lépés vagy null, ha nincs szükség blokkolásra
     */
    private Move findBlockingMove(final Tabla board, final char enemySymbol) {
        int rows = board.getRows();
        int cols = board.getCols();
        int[][] directions = {{0, 1}, {1, 0}, {1, 1}, {1, -1}};

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (board.getCell(r, c) != enemySymbol) {
                    continue;
                }

                for (int[] d : directions) {
                    int dr = d[0];
                    int dc = d[1];
                    int count = 1;

                    for (int k = 1; k < WIN_LENGTH; k++) {
                        int rr = r + dr * k;
                        int cc = c + dc * k;
                        if (!board.isValid(rr, cc)) {
                            break;
                        }
                        if (board.getCell(rr, cc) == enemySymbol) {
                            count++;
                        } else {
                            break;
                        }
                    }

                    if (count >= BLOCK_THRESHOLD) {
                        int br = r - dr;
                        int bc = c - dc;
                        if (board.isValid(br, bc) && board.isEmpty(br, bc)) {
                            return new Move(br, bc);
                        }

                        br = r + dr * count;
                        bc = c + dc * count;
                        if (board.isValid(br, bc) && board.isEmpty(br, bc)) {
                            return new Move(br, bc);
                        }
                    }
                }
            }
        }
        return null;
    }

    /**
     * Formázza a lépést emberi olvasható formátumba (pl. "A1").
     *
     * @param move a lépés
     * @return a formázott lépés sztring
     */
    private String formatMove(final Move move) {
        return "" + (char) ('A' + move.col()) + (move.row() + 1);
    }
}
