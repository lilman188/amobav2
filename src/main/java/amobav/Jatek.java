package amobav;

/**
 * A Jatek osztály az amőba játék fő logikáját valósítja meg.
 * Inicializálja a táblát, kezeli a játékosok lépéseit
 * és vezérli a játék menetét.
 */
public final class Jatek {

    /** A tábla sorainak száma. */
    private final int rows;

    /** A tábla oszlopainak száma. */
    private final int cols;

    /** A játékot reprezentáló tábla. */
    private final Tabla tabla;

    /** Az emberi játékos. */
    private final Jatekos ember;

    /** A gépi játékos. */
    private final Jatekos gep;

    /**
     * Létrehoz egy új játék példányt a megadott méretekkel és játékosokkal.
     *
     * @param sorok a tábla sorainak száma
     * @param oszlopok a tábla oszlopainak száma
     * @param emberSzimb az emberi játékos szimbóluma
     * @param gepSzimb a gépi játékos szimbóluma
     */
    public Jatek(final int sorok, final int oszlopok,
                 final char emberSzimb, final char gepSzimb) {
        this.rows = sorok;
        this.cols = oszlopok;
        this.tabla = new Tabla(this.rows, this.cols);
        this.ember = new Ember(emberSzimb);
        this.gep = new Gep(gepSzimb);
    }

    /**
     * Elindítja a játékot, felváltva kéri a lépéseket a játékosoktól
     * és ellenőrzi a nyerési feltételeket.
     */
    public void start() {
        boolean emberKovetkezik = true;
        tabla.print();

        while (true) {
            Jatekos aktualis = emberKovetkezik ? ember : gep;
            Move move = aktualis.getMove(tabla);
            tabla.placeMark(move.row(), move.col(), aktualis.getSymbol());
            tabla.print();

            if (tabla.checkWin(aktualis.getSymbol())) {
                System.out.println((emberKovetkezik ? "Ember" : "Gép")
                        + " nyert!");
                break;
            }

            if (tabla.getAvailableMoves().isEmpty()) {
                System.out.println("Döntetlen!");
                break;
            }

            emberKovetkezik = !emberKovetkezik;
        }
    }
}
