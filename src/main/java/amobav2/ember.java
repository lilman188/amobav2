package amobav2;

import java.util.Scanner;

public class ember extends jatekos {
    private final Scanner scanner = new Scanner(System.in);

    public ember(char symbol) {
        super(symbol);
    }

    @Override
    public move getMove(tabla board) {
        while (true) {
            System.out.print("Add meg a lépést (pl. A5): ");
            String input = scanner.nextLine().trim().toUpperCase();

            if (input.length() < 2) {
                System.out.println("Hibás formátum!");
                continue;
            }

            char colChar = input.charAt(0);
            int row;
            try {
                row = Integer.parseInt(input.substring(1)) - 1;
            } catch (NumberFormatException e) {
                System.out.println("Hibás formátum!");
                continue;
            }

            int col = colChar - 'A';
            if (board.placeMark(row, col, getSymbol())) {
                return new move(row, col);
            } else {
                System.out.println("Nem tehető ide korong!");
            }
        }
    }
}
