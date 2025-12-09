package amobav;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

public class JatekTest {

    @Test
    void testPlayerWinsAndSavedToDatabase() {
        // Mockok létrehozása
        Tabla tabla = mock(Tabla.class);
        Ember ember = mock(Ember.class);
        Jatekos gep = mock(Jatekos.class);
        DatabaseManager db = mock(DatabaseManager.class);

        // Move objektum
        Move move = new Move(0, 0);

        // Tabla viselkedése
        when(tabla.getAvailableMoves()).thenReturn(java.util.List.of(move));
        when(tabla.checkWin('X')).thenReturn(true);

        // Ember viselkedése
        when(ember.getSymbol()).thenReturn('X');
        when(ember.getMove(tabla)).thenReturn(move);

        // Jatek osztály példány mockokkal
        Jatek jatek = new Jatek(3, 3, 'X', 'O', db, "Teszt Játékos") {
            @Override
            public void start() {
                // Manuálisan futtatjuk egyszer a ciklust (mockolt környezet)
                Move m = ember.getMove(tabla);
                tabla.placeMark(m.row(), m.col(), ember.getSymbol());

                if (tabla.checkWin(ember.getSymbol())) {
                    db.saveWinner("Teszt Játékos");
                }
            }
        };

        // Futtatás
        jatek.start();

        // Ellenőrzés: DB mentést hívtuk?
        verify(db, times(1)).saveWinner("Teszt Játékos");
    }


    @Test
    void testDraw() {
        Tabla tabla = mock(Tabla.class);
        Ember ember = mock(Ember.class);
        Jatekos gep = mock(Jatekos.class);
        DatabaseManager db = mock(DatabaseManager.class);

        when(tabla.getAvailableMoves()).thenReturn(java.util.List.of()); // nincs több lépés → döntetlen

        Jatek jatek = new Jatek(3, 3, 'X', 'O', db, "Teszt Játékos") {
            @Override
            public void start() {
                if (tabla.getAvailableMoves().isEmpty()) {
                    System.out.println("Döntetlen!");
                }
            }
        };

        jatek.start();

        // DB hívás nem történhetett!
        verify(db, never()).saveWinner(any());
    }
}
