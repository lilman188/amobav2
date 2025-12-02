package amobav;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A {@code Gep} osztály egy egyszerű MI-t valósít meg,
 * amely megpróbálja megakadályozni, hogy az ember
 * 5 darab 'X' jelet egymás mellé tudjon rakni.
 * Először védekezik, majd ha nincs veszély, véletlenszerűen lép.
 */
public class Gep extends Jatekos {

    /**
     * A gépi játékos véletlenszerű lépéséhez használt generátor.
     */
    private final Random random = new Random();

    /**
     * A blokkoláshoz vizsgált maximális sorhossz.
     */
    private static final int MAX_CHAIN = 5;

    /**
     * Létrehoz egy gépi játékost.
     *
     * @param symbol a gép szimbóluma (például 'O')
     */
    public Gep(char symbol) {
        super(symbol);
    }

    /**
     * Meghatározza a gép következő lépését.
     * Először blokkolja az emberi játékos erős sorait, majd ha nincs veszély,
     * véletlenszerű pozíciót választ.
     *
     * @param board a játék tábla
     * @return a gép által kiválasztott lépés
     */
    @Override
    public Move getMove(Tabla board) {
        Move blockMove = findBlockingMove(board, 'X');
        if (blockMove != null) {
            System.out.println("Gép blokkol: " + formatMove(blockMove));
            return blockMove;
        }

        List<Move> possibleMoves = board.getAvailableMoves();
        Move randomMove = possibleMoves.get(random.nextInt(possibleMoves.size()));

        System.out.println("Gép véletlen lépése: " + formatMove(randomMove));
        return randomMove;
    }

    /**
     * Megkeresi, hogy az ellenfél közel áll-e 5 elemű sorhoz.
     * Ha talál 3 vagy 4 egymás melletti jelet, melynek két vége közül valamelyik üres,
     * akkor visszaadja a blokkoló mezőt.
     *
     * @param board a tábla
     * @param enemySymbol az ember szimbóluma ('X')
     * @return a blokkoló lépés vagy {@code null}, ha nincs veszély
     */
    private Move findBlockingMove(Tabla board, char enemySymbol) {

        int rows = board.getRows();
        int cols = board.getCols();

        int[][] directions = {
                {0, 1},   // vízszintes
                {1, 0},   // függőleges
                {1, 1},   // főátló
                {1, -1}   // mellékátló
        };

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {

                if (board.getCell(row, col) != enemySymbol) {
                    continue;
                }

                for (int[] dir : directions) {
                    int dr = dir[0];
                    int dc = dir[1];

                    int count = 1;
                    int checkRow;
                    int checkCol;

                    List<Move> chain = new ArrayList<>();
                    chain.add(new Move(row, col));

                    // Tovább vizsgáljuk a sort
                    for (int k = 1; k < MAX_CHAIN; k++) {
                        checkRow = row + dr * k;
                        checkCol = col + dc * k;

                        if (!board.isValid(checkRow, checkCol)) {
                            break;
                        }

                        if (board.getCell(checkRow, checkCol) == enemySymbol) {
                            count++;
                            chain.add(new Move(checkRow, checkCol));
                        } else {
                            break;
                        }
                    }

                    // Ha kialakult minimum 3 elemű veszélyes lánc
                    if (count >= 3) {

                        // Sor eleje
                        int blockRowStart = row - dr;
                        int blockColStart = col - dc;

                        if (board.isValid(blockRowStart, blockColStart)
                                && board.isEmpty(blockRowStart, blockColStart)) {
                            return new Move(blockRowStart, blockColStart);
                        }

                        // Sor vége
                        int blockRowEnd = row + dr * count;
                        int blockColEnd = col + dc * count;

                        if (board.isValid(blockRowEnd, blockColEnd)
                                && board.isEmpty(blockRowEnd, blockColEnd)) {
                            return new Move(blockRowEnd, blockColEnd);
                        }
                    }
                }
            }
        }

        return null;
    }

    /**
     * Formázza a lépést ember által olvasható alakra (pl. A5).
     *
     * @param move a lépés
     * @return az olvasható formátum
     */
    private String formatMove(Move move) {
        char column = (char) ('A' + move.col());
        int row = move.row() + 1;
        return String.valueOf(column) + row;
    }
}
