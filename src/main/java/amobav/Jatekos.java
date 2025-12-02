package amobav;

/**
 * Absztrakt alaposztály, amely egy általános játékost reprezentál.
 * Minden játékos rendelkezik egy saját szimbólummal, és képes lépést
 * választani.
 */
public abstract class Jatekos {

    /**
     * A játékos által használt szimbólum (X vagy O).
     */
    private final char symbol;

    /**
     * Létrehoz egy új játékost a megadott szimbólummal.
     *
     * @param symbol a játékos szimbóluma ('X' vagy 'O')
     */
    public Jatekos(char symbol) {
        this.symbol = symbol;
    }

    /**
     * Visszaadja a játékos által használt szimbólumot.
     *
     * @return a játékos szimbóluma
     */
    public char getSymbol() {
        return symbol;
    }

    /**
     * Bekéri a játékostól a következő lépést.
     *
     * @param board a játék aktuális táblája
     * @return a játékos által választott lépés
     */
    public abstract Move getMove(Tabla board);
}
