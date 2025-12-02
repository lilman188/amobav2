package amobav;

import java.util.Scanner;

/**
 * Emberi játékos osztály.
 * A felhasználótól kéri a lépéseket: sor szám és oszlop betű formátumban.
 */
public final class Ember extends Jatekos { // final, mert nem lesz kiterjesztve
    /** Beolvasáshoz használt Scanner objektum. */
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
     * Bekéri a felhasználótól a következő lépést.
     *
     * @param board a játék aktuális állása
     * @return a kiválasztott lépés (Move objektum)
     */
    @Override
    public Move getMove(final Tabla board) {
        int row = -1;
        int col = -1;

        while (true) {
            try {
                // Sor bekérése (1-től board.getRows()-ig)
                System.out.print("Sor (1-" + board.getRows() + "): ");
                row = scanner.nextInt() - 1; // index 0-tól
                scanner.nextLine(); // puffer ürítése

                // Oszlop bekérése (A-tól megfelelő betűig)
                System.out.print("Oszlop (A-" + (char)
                        ('A' + board.getCols() - 1) + "): ");
                String input = scanner.nextLine().toUpperCase(); // nagybetűs
                if (input.length() != 1 || input.charAt(0)
                        < 'A' || input.charAt(0) >= 'A' + board.getCols()) {
                    System.out.println("Érvénytelen oszlop!");
                    continue;
                }
                col = input.charAt(0) - 'A'; // betűből szám

                // Érvényesség ellenőrzése
                if (!board.isValid(row, col) || !board.isEmpty(row, col)) {
                    System.out.println("Érvénytelen lépés!");
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
