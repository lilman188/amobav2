package amobav2;

public abstract class Player {
    private final char symbol;

    public Player(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    public abstract Move getMove(Board board);
}
