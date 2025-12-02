package amobav;

/**
 * A program belépési pontja.
 */
public final class Main {

    /**
     * Privát konstruktor, hogy ne lehessen példányosítani.
     */
    private Main() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * A program main metódusa. Létrehoz és elindít egy új játékot.
     *
     * @param args a parancssori argumentumok (nem használt)
     */
    public static void main(String[] args) {
        Jatek game = new Jatek(10, 10); // 10×10 tábla
        game.start();
    }
}
