package amobav2;

import java.util.List;
import java.util.Random;

public class gep extends jatekos {
    private final Random random = new Random();

    public gep(char symbol) {
        super(symbol);
    }

    @Override
    public move getMove(tabla board) {
        List<move> possibleMoves = board.getAvailableMoves();
        if (possibleMoves.isEmpty()) return null;

        move move = possibleMoves.get(random.nextInt(possibleMoves.size()));
        System.out.println("Gép lépése: " + (char)('A' + move.col()) + (move.row() + 1));
        return move;
    }
}
