package amobav;

/**
 * A játék fő logikáját kezelő osztály.
 */
public class Jatek {

    /** A játék táblája. */
    private final Tabla tabla;

    /** Az emberi játékos. */
    private final Jatekos ember;

    /** A gépi játékos. */
    private final Jatekos gep;

    /**
     * Létrehoz egy új játékot a megadott paraméterekkel.
     *
     * @param rows tábla sorainak száma
     * @param cols tábla oszlopainak száma
     * @param emberSymbol emberi játékos szimbóluma
     * @param gepSymbol gépi játékos szimbóluma
     */
    public Jatek(final int rows, final int cols, final char emberSymbol,
                 final char gepSymbol) {
        this.tabla = new Tabla(rows, cols);
        this.ember = new Ember(emberSymbol);
        this.gep = new Gep(gepSymbol);
    }

    /**
     * Elindítja a játékot.
     */
    public void start() {
        Jatekos currentPlayer = ember;

        while (true) {
            tabla.print();
            System.out.println("Játékos " + currentPlayer.getSymbol()
                    + " következik:");

            Move move = currentPlayer.getMove(tabla);
            tabla.placeMark(move.row(), move.col(), currentPlayer.getSymbol());

            if (tabla.checkWin(currentPlayer.getSymbol())) {
                tabla.print();
                System.out.println(
                        "A játékos " + currentPlayer.getSymbol() + " nyert!");
                break;
            }

            if (tabla.getAvailableMoves().isEmpty()) {
                tabla.print();
                System.out.println("Döntetlen!");
                break;
            }

            // váltás a másik játékosra
            currentPlayer = (currentPlayer == ember) ? gep : ember;
        }
    }
}
