package amobav;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TablaTest {

    private Tabla tabla;

    @BeforeEach
    void setUp() {
        tabla = new Tabla(5, 5); // 5x5 tábla
    }

    @Test
    void testPlaceMarkAndGetCell() {
        assertTrue(tabla.placeMark(0, 0, 'X'));
        assertEquals('X', tabla.getCell(0, 0));

        // Nem lehet felülírni a már foglalt mezőt
        assertFalse(tabla.placeMark(0, 0, 'O'));
    }

    @Test
    void testIsEmpty() {
        assertTrue(tabla.isEmpty(1, 1));
        tabla.placeMark(1, 1, 'O');
        assertFalse(tabla.isEmpty(1, 1));
    }

    @Test
    void testCheckWinHorizontal() {
        // 4 egymás melletti X jel nyeréshez (WIN_LENGTH=4)
        tabla.placeMark(0, 0, 'X');
        tabla.placeMark(0, 1, 'X');
        tabla.placeMark(0, 2, 'X');
        tabla.placeMark(0, 3, 'X');
        assertTrue(tabla.checkWin('X'));
    }

    @Test
    void testCheckWinVertical() {
        tabla.placeMark(0, 0, 'O');
        tabla.placeMark(1, 0, 'O');
        tabla.placeMark(2, 0, 'O');
        tabla.placeMark(3, 0, 'O');
        assertTrue(tabla.checkWin('O'));
    }

    @Test
    void testGetAvailableMoves() {
        assertEquals(25, tabla.getAvailableMoves().size());
        tabla.placeMark(0, 0, 'X');
        assertEquals(24, tabla.getAvailableMoves().size());
    }
}
