package amobav;

import java.util.List;
import java.util.Random;

/**
 * Gépi játékos, teljesen véletlenszerű lépésekkel.
 */
public final class Gep extends Jatekos {
    private final Random random = new Random();

    public Gep(final char playerSymbol) {
        super(playerSymbol);
    }

    @Override
    public Move getMove(final Tabla board) {
        List<Move> possibleMoves = board.getAvailableMoves();
        Move move = possibleMoves.get(random.nextInt(possibleMoves.size()));
        System.out.println("Gép lépése: " + (char)('A' + move.col()) + (move.row() + 1));
        return move;
    }
}
