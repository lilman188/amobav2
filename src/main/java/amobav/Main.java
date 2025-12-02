package amobav;

/**
 * A játék indításáért felelős osztály.
 */
public class Main {

    public static void main(final String[] args) {
        final int rows = 10;
        final int cols = 10;

        // Emberi és gépi játékos szimbólumok
        final char emberSymbol = 'X';
        final char gepSymbol = 'O';

        Jatek game = new Jatek(rows, cols, emberSymbol, gepSymbol);
        game.start();
    }
}
