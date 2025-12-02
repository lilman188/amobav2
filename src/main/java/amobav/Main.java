package amobav;

/**
 * A játék indításáért felelős osztály.
 */
public final class Main {

    // Privát konstruktor, hogy a Main osztályt ne lehessen példányosítani
    private Main() {
        throw new UnsupportedOperationException(
                "Utility class cannot be instantiated");
    }

    /**
     * Program belépési pontja.
     *
     * @param args parancssori argumentumok (nem használjuk)
     */
    static void main(final String[] args) {
        final int rows = 10;
        final int cols = 10;

        // Emberi és gépi játékos szimbólumok
        final char emberSymbol = 'X';
        final char gepSymbol = 'O';

        Jatek game = new Jatek(rows, cols, emberSymbol, gepSymbol);
        game.start();
    }
}
