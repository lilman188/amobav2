package amobav;

/**
 * A játék indításáért felelős osztály.
 * Utility osztályként használva, nem példányosítható.
 */
public final class Main {

    /**
     * Privát konstruktor, hogy ne lehessen példányosítani a utility osztályt.
     */
    private Main() {
        // Üres
    }

    /**
     * A játékot indító metódus.
     *
     * @param args parancssori argumentumok (nem használtak)
     */
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
