package amobav2;

public class jatek {
    private final tabla board;
    private final jatekos human;
    private final jatekos computer;
    private jatekos currentPlayer;

    public jatek(int rows, int cols) {
        this.board = new tabla(rows, cols);
        this.human = new ember('X');
        this.computer = new gep('O');
        this.currentPlayer = human;
    }

    public void start() {
        System.out.println("Amőba játék indul! (X = ember, O = gép)");
        board.initialize();

        // Ember kezd automatikusan középen
        int midRow = board.getRows() / 2;
        int midCol = board.getCols() / 2;
        board.placeMark(midRow, midCol, human.getSymbol());
        board.print();

        boolean gameOver = false;

        while (!gameOver) {
            move move = currentPlayer.getMove(board);
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

    private void switchPlayer() {
        currentPlayer = (currentPlayer == human) ? computer : human;
    }
}
