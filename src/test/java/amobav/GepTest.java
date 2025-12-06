package amobav;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GepTest {

    private Tabla tabla;
    private Gep gep;

    @BeforeEach
    void setUp() {
        tabla = new Tabla(5, 5);
        gep = new Gep('O');
    }

    @Test
    void testGetMoveReturnsValidMove() {
        Move move = gep.getMove(tabla);
        assertNotNull(move);
        assertTrue(tabla.isValid(move.row(), move.col()));
        assertTrue(tabla.isEmpty(move.row(), move.col()));
    }
}
