package amobav;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class JatekTest {

    private DatabaseManager db;
    private String playerName;

    @BeforeEach
    void setUp() {
        // Mock-oljuk az adatbázist, hogy ne kelljen valódi DB
        db = mock(DatabaseManager.class);
        playerName = "TesztJatekos";
    }

    @Test
    void testJatekLétrehozás() {
        int rows = 4;
        int cols = 4;
        char emberSymbol = 'X';
        char gepSymbol = 'O';

        // Predefiniált input a teszthez: sor 1, oszlop A
        String input = "1\nA\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        Ember ember = new Ember(emberSymbol, scanner);
        Gep gep = new Gep(gepSymbol); // random lépések

        Jatek game = new Jatek(rows, cols, ember.getSymbol(), gep.getSymbol(), db, playerName);

        assertNotNull(game, "A játék példánynak nem szabad nullnak lennie");
    }

    @Test
    void testJatekStartNemDob() {
        int rows = 4;
        int cols = 4;
        char emberSymbol = 'X';
        char gepSymbol = 'O';

        // Predefiniált input a teszthez
        String input = "1\nA\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        Ember ember = new Ember(emberSymbol, scanner);
        Gep gep = new Gep(gepSymbol); // random lépések

        Jatek game = new Jatek(rows, cols, ember, gep, db, playerName);

        // Ellenőrizzük, hogy a start() nem dob kivételt
        assertDoesNotThrow(game::start, "A játék elindítása nem szabad, hogy hibát dobjon");
    }
}
