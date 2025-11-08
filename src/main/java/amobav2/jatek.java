package hu.egyetem.amobav2;

public class Game {
    private final Board board;
    private final Player human;
    private final Player computer;
    private Player currentPlayer;

    public Game(int rows, int cols) {
        this.board = new Board(rows, cols);
        this.human = new HumanPlayer('X');
        this.computer = new ComputerPlayer('O');
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

    private void switchPlayer() {
        currentPlayer = (currentPlayer == human) ? computer : human;
    }
}
