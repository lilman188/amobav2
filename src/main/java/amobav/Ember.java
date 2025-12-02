package amobav;

import java.util.Scanner;

/**
 * Emberi játékost reprezentáló osztály.
 * Kéri a felhasználótól a következő lépést a táblán.
 */
public final class Ember extends Jatekos {
    /**
     * Létrehoz egy Scanner objektumot.
     */
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Létrehoz egy új emberi játékost a megadott szimbólummal.
     *
     * @param playerSymbol a játékos szimbóluma
     */
    public Ember(final char playerSymbol) {
        super(playerSymbol);
    }

    /**
     * Kéri az emberi játékostól a következő lépést.
     *
     * @param board a játéktábla, amin a lépést végrehajtjuk
     * @return a kiválasztott lépés Move formátumban
     */
    @Override
    public Move getMove(final Tabla board) {
        int row;
        int col;

        while (true) {
            System.out.print(
                    "Adja meg a sor számát (1-" + board.getRows() + "): ");
            row = scanner.nextInt() - 1;

            System.out.print("Adja meg az oszlop betűjét (A-" + (char)
                    ('A' + board.getCols() - 1) + "): ");
            col = Character.toUpperCase(scanner.next().charAt(0)) - 'A';

            if (board.isValid(row, col) && board.isEmpty(row, col)) {
                break;
            } else {
                System.out.println("Érvénytelen lépés. Próbálja újra!");
            }
        }

        return new Move(row, col);
    }
}
