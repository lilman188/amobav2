package amobav;

import java.util.Scanner;

/**
 * Emberi játékos, aki a konzolról adja meg a lépéseit.
 */
public class Ember extends Jatekos {

    /** A felhasználói bemenet olvasásához. */
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Létrehoz egy új emberi játékost a megadott szimbólummal.
     *
     * @param playerSymbol a játékos szimbóluma
     */
    public Ember(final char playerSymbol) {
        super(playerSymbol);
    }

    @Override
    public Move getMove(final Tabla board) {
        while (true) {
            System.out.print("Lépj egyet (pl. A5): ");
            String input = scanner.nextLine().trim().toUpperCase();

            if (input.length() < 2) {
                System.out.println("Hibás formátum!");
                continue;
            }

            char colChar = input.charAt(0);
            int row;

            try {
                row = Integer.parseInt(input.substring(1)) - 1;
            } catch (NumberFormatException e) {
                System.out.println("Hibás formátum!");
                continue;
            }

            int col = colChar - 'A';

            if (row < 0 || row >= board.getRows()
                    || col < 0 || col >= board.getCols()) {
                System.out.println("A megadott mező nem létezik!");
                continue;
            }

            if (!board.isEmpty(row, col)) {
                System.out.println("Ez a mező foglalt!");
                continue;
            }

            return new Move(row, col);
        }
    }
}
