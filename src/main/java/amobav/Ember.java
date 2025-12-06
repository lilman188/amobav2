package amobav;

import java.util.Scanner;

/**
 * Emberi játékos osztály.
 * A felhasználótól kéri a lépéseket: sor szám és oszlop betű formátumban.
 */
public final class Ember extends Jatekos {

    /** Játékos neve. */
    private final String name;

    /** Beolvasáshoz használt Scanner objektum. */
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Létrehoz egy új emberi játékost a megadott névvel és szimbólummal.
     *
     * @param name a játékos neve
     * @param playerSymbol a játékos szimbóluma
     */
    public Ember(final String name, final char playerSymbol) {
        super(playerSymbol);
        this.name = name;
    }

    /**
     * Visszaadja a játékos nevét.
     *
     * @return a játékos neve
     */
    public String getName() {
        return name;
    }

    @Override
    public Move getMove(final Tabla board) {
        int row = -1;
        int col = -1;

        while (true) {
            try {
                // Sor bekérése
                System.out.print(name + ", add meg a sor számát (1-" + board.getRows() + "): ");
                row = scanner.nextInt() - 1; // 0-tól indexelés
                scanner.nextLine(); // puffer ürítése

                // Oszlop bekérése (A-tól megfelelő betűig)
                System.out.print("Add meg az oszlop betűjét (A-" + (char) ('A' + board.getCols() - 1) + "): ");
                String input = scanner.nextLine().toUpperCase();

                if (input.length() != 1 || input.charAt(0) < 'A' || input.charAt(0) >= 'A' + board.getCols()) {
                    System.out.println("Érvénytelen oszlop!");
                    continue;
                }
                col = input.charAt(0) - 'A';

                // Érvényesség ellenőrzése
                if (!board.isValid(row, col) || !board.isEmpty(row, col)) {
                    System.out.println("Ez a mező már foglalt vagy érvénytelen!");
                    continue;
                }

                break; // helyes lépés
            } catch (Exception e) {
                System.out.println("Hibás input, próbáld újra!");
                scanner.nextLine(); // puffer ürítése
            }
        }

        return new Move(row, col);
    }
}
