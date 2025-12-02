package amobav;

import java.util.Scanner;

/**
 * Az {@code Ember} osztály egy emberi játékost reprezentál,
 * aki a konzolon keresztül adja meg a lépéseit.
 */
public class Ember extends Jatekos {

    /** A minimum bemeneti hossz (pl. "A1"). */
    private static final int MIN_INPUT_LENGTH = 2;

    /** A sorszám átalakításához használt eltolás (1 → index 0). */
    private static final int ROW_OFFSET = 1;

    /** A betű alapú oszlop indexelés első karaktere. */
    private static final char FIRST_COLUMN = 'A';

    /**
     * A felhasználói bemenet olvasásához használt Scanner példány.
     * Nem zárjuk be, mert a System.in lezárása a program további
     * működését akadályozná.
     */
    @SuppressWarnings("resource")
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Létrehoz egy új emberi játékost a megadott játékszimbólummal.
     *
     * @param symbol a játékoshoz tartozó karakter (pl. 'X' vagy 'O')
     */
    public Ember(char symbol) {
        super(symbol);
    }

    /**
     * Bekéri a játékostól a következő lépést. A bemenetet
     * szöveges formában kell megadni (például: A5).
     * A metódus ellenőrzi a formátumot, valamint hogy a célmező
     * létezik-e és üres-e.
     *
     * @param board a jelenlegi játéktábla
     * @return egy érvényes {@link Move} objektum, amely a játékos lépését képviseli
     */
    @Override
    public Move getMove(Tabla board) {
        while (true) {
            System.out.print("Lépj egyet (pl. A5): ");
            String input = scanner.nextLine().trim().toUpperCase();

            if (input.length() < MIN_INPUT_LENGTH) {
                System.out.println("Hibás formátum! (pl. A5)");
                continue;
            }

            char colChar = input.charAt(0);
            int row;

            try {
                row = Integer.parseInt(input.substring(1)) - ROW_OFFSET;
            } catch (NumberFormatException e) {
                System.out.println("Hibás formátum! A sor szám legyen.");
                continue;
            }

            int col = colChar - FIRST_COLUMN;

            // Koordináta tartományon kívül
            if (row < 0 || row >= board.getRows()
                    || col < 0 || col >= board.getCols()) {
                System.out.println("A megadott mező nem létezik!");
                continue;
            }

            // Foglalt mező
            if (!board.isEmpty(row, col)) {
                System.out.println("Ez a mező foglalt!");
                continue;
            }

            return new Move(row, col);
        }
    }
}
