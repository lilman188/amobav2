package amobav;

/**
 * Absztrakt osztály, amely egy játékost reprezentál.
 * Minden játékos rendelkezik szimbólummal és képes lépést adni.
 */
public abstract class Jatekos {

    /** A játékos szimbóluma */
    private final char symbol;

    /**
     * Létrehoz egy új játékost a megadott szimbólummal.
     *
     * @param playerSymbol a játékos szimbóluma
     */
    public Jatekos(final char playerSymbol) {
        this.symbol = playerSymbol;
    }

    /**
     * Visszaadja a játékos szimbólumát.
     *
     * @return a játékos szimbóluma
     */
    public char getSymbol() {
        return symbol;
    }

    /**
     * Visszaadja a játékos lépését a táblán.
     *
     * @param board a játék tábla
     * @return a lépés
     */
    public abstract Move getMove(final Tabla board);
}
