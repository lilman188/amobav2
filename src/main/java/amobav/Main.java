package amobav;

import java.util.Scanner;

/**
 * A játék indításáért felelős osztály.
 */
public final class Main {

    private Main() {
        throw new UnsupportedOperationException(
                "Utility class cannot be instantiated");
    }

    public static void main(final String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Humán játékos neve
        System.out.print("Add meg a neved: ");
        String playerName = scanner.nextLine().trim();

        final int rows = 5;
        final int cols = 5;

        // Emberi és gépi játékos szimbólumok
        final char emberSymbol = 'X';
        final char gepSymbol = 'O';

        // Adatbázis inicializálása
        DatabaseManager db = new DatabaseManager();

        // Játékos létrehozása névvel
        Jatek game = new Jatek(rows, cols, emberSymbol, gepSymbol, db, playerName);
        game.start();

        // High score kiírása
        db.printHighScores();
        db.close();
    }
}
