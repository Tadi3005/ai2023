package treasurequest.domains;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoordinateTest {
    private final Coordinate coordinate = new Coordinate(5, 1);
    @Test
    void getRow() {
        assertEquals(5, coordinate.getRow());
    }

    @Test
    void getCol() {
        assertEquals(1, coordinate.getCol());
    }

    @Test
    void move() {
        assertEquals(new Coordinate(6, 2), coordinate.move(1, 1));
    }

    @Test
    void testEquals() {
        assertEquals(new Coordinate(5, 1), coordinate);
    }

    @Test
    void testHashCode() {
        Coordinate coordinate1 = new Coordinate(5, 1);
        assertTrue(coordinate.equals(coordinate1) && coordinate.hashCode() == coordinate1.hashCode());
    }

    @Test
    void distance() {
        assertEquals(1, coordinate.distance(new Coordinate(5, 2)));
    }
    
    @Test
    void isAdjacentTest() {
        assertTrue(coordinate.isAdjacent(new Coordinate(5, 2)));
        assertTrue(coordinate.isAdjacent(new Coordinate(4, 1)));
        assertTrue(coordinate.isAdjacent(new Coordinate(4, 2)));
        assertTrue(coordinate.isAdjacent(new Coordinate(6, 1)));
        assertTrue(coordinate.isAdjacent(new Coordinate(6, 2)));
        assertTrue(coordinate.isAdjacent(new Coordinate(5, 1)));
        assertFalse(coordinate.isAdjacent(new Coordinate(4, 3)));
        assertFalse(coordinate.isAdjacent(new Coordinate(6, 3)));
        assertFalse(coordinate.isAdjacent(new Coordinate(7, 1)));
    }
}