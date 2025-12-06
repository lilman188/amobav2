package amobav;

public class Jatek {

    private final Tabla tabla;
    private final Ember ember;
    private final Jatekos gep;
    private final DatabaseManager db;
    private final String playerName; // új

    public Jatek(final int rows, final int cols, final char emberSymbol,
                 final char gepSymbol, final DatabaseManager db, final String playerName) {
        this.tabla = new Tabla(rows, cols);
        this.ember = new Ember(playerName, emberSymbol);
        this.gep = new Gep(gepSymbol);
        this.db = db;
        this.playerName = playerName;
    }

    public void start() {
        Jatekos currentPlayer = ember;

        while (true) {
            tabla.print();
            System.out.println("Játékos " + currentPlayer.getSymbol() + " következik:");

            Move move = currentPlayer.getMove(tabla);
            tabla.placeMark(move.row(), move.col(), currentPlayer.getSymbol());

            if (tabla.checkWin(currentPlayer.getSymbol())) {
                tabla.print();
                String winnerName = (currentPlayer instanceof Ember) ? playerName : "Gép";
                System.out.println("A játékos " + winnerName + " nyert!");
                db.saveWinner(winnerName);
                break;
            }

            if (tabla.getAvailableMoves().isEmpty()) {
                tabla.print();
                System.out.println("Döntetlen!");
                break;
            }

            currentPlayer = (currentPlayer == ember) ? gep : ember;
        }
    }
}
