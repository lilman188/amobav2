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
        final int rows = 5;
        final int cols = 5;

        // Emberi és gépi játékos szimbólumok
        final char emberSymbol = '0';
        final char gepSymbol = 'X';

        Jatek game = new Jatek(rows, cols, emberSymbol, gepSymbol);
        game.start();
    }
}
