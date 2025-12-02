package amobav;

/**
 * A {@code Jatek} osztály kezeli a játék fő folyamatát:
 * - a tábla létrehozása,
 * - a játékosok váltása,
 * - a lépések feldolgozása,
 * - a győzelem ellenőrzése.
 */
public class Jatek {

    /**
     * A játék táblája.
     */
    private final Tabla board;

    /**
     * Az emberi játékos.
     */
    private final Jatekos human;

    /**
     * A gépi játékos.
     */
    private final Jatekos computer;

    /**
     * A soron következő játékos.
     */
    private Jatekos currentPlayer;

    /**
     * Létrehoz egy új játékot a megadott mérettel.
     *
     * @param rows a tábla sorainak száma
     * @param cols a tábla oszlopainak száma
     */
    public Jatek(int rows, int cols) {
        this.board = new Tabla(rows, cols);
        this.human = new Ember('X');
        this.computer = new Gep('O');
        this.currentPlayer = human;
    }

    /**
     * Elindítja a játékot.
     * Az ember automatikusan a középpontba helyezi az első lépést,
     * majd a játékosok felváltva következnek.
     */
    public void start() {
        System.out.println("Amőba játék indul! (X = ember, O = gép)");

        board.initialize();

        // Az ember kezd a tábla közepén.
        int midRow = board.getRows() / 2;
        int midCol = board.getCols() / 2;

        board.placeMark(midRow, midCol, human.getSymbol());
        board.print();

        boolean gameOver = false;

        while (!gameOver) {
            Move move = currentPlayer.getMove(board);

            if (move == null) {
                System.out.println("Nincs több lehetséges lépés!");
                break;
            }

            board.placeMark(move.row(), move.col(), currentPlayer.getSymbol());
            board.print();

            if (board.checkWin(currentPlayer.getSymbol())) {
                System.out.println("Győzött: " + currentPlayer.getSymbol());
                gameOver = true;
            } else {
                switchPlayer();
            }
        }
    }

    /**
     * A soron következő játékos átváltása.
     */
    private void switchPlayer() {
        if (currentPlayer == human) {
            currentPlayer = computer;
        } else {
            currentPlayer = human;
        }
    }
}
