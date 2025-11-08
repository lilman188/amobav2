package amobav2;

public abstract class jatekos {
    private final char symbol;

    public jatekos(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    public abstract move getMove(tabla board);
}
