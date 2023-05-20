package treasurequest.domains;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CaseTest {


    @Test
    void getCaseType() {
        assertEquals(CaseType.WATER, new Case(CaseType.WATER).getCaseType());
        assertEquals(CaseType.SAND, new Case(CaseType.SAND).getCaseType());
        assertEquals(CaseType.GRASSLAND, new Case(CaseType.GRASSLAND).getCaseType());
        assertEquals(CaseType.FOREST, new Case(CaseType.FOREST).getCaseType());
        assertEquals(CaseType.ROCK, new Case(CaseType.ROCK).getCaseType());
        assertEquals(CaseType.UNKNOWN, new Case(CaseType.UNKNOWN).getCaseType());
    }

    @Test
    void getCost() {
        assertEquals(0, new Case(CaseType.WATER).getCost());
        assertEquals(1, new Case(CaseType.SAND).getCost());
        assertEquals(2, new Case(CaseType.GRASSLAND).getCost());
        assertEquals(3, new Case(CaseType.FOREST).getCost());
        assertEquals(5, new Case(CaseType.ROCK).getCost());
        assertEquals(0, new Case(CaseType.UNKNOWN).getCost());
    }

    @Test
    void isDigable() {
        assertFalse(new Case(CaseType.WATER).isDigable());
        assertTrue(new Case(CaseType.SAND).isDigable());
        assertTrue(new Case(CaseType.GRASSLAND).isDigable());
        assertTrue(new Case(CaseType.FOREST).isDigable());
        assertTrue(new Case(CaseType.ROCK).isDigable());
        assertFalse(new Case(CaseType.UNKNOWN).isDigable());
    }

    @Test
    void setAmountTreasure() {
        assertEquals(0, new Case(CaseType.WATER).getAmountTreasure());
        assertEquals(0, new Case(CaseType.SAND).getAmountTreasure());
        assertEquals(0, new Case(CaseType.GRASSLAND).getAmountTreasure());
        assertEquals(0, new Case(CaseType.FOREST).getAmountTreasure());
        assertEquals(0, new Case(CaseType.ROCK).getAmountTreasure());
        assertEquals(0, new Case(CaseType.UNKNOWN).getAmountTreasure());
    }

    @Test
    void getType() {
        assertEquals("Water", new Case(CaseType.WATER).getType());
        assertEquals("Sand", new Case(CaseType.SAND).getType());
        assertEquals("Grassland", new Case(CaseType.GRASSLAND).getType());
        assertEquals("Forest", new Case(CaseType.FOREST).getType());
        assertEquals("Rock", new Case(CaseType.ROCK).getType());
        assertEquals("Unknown", new Case(CaseType.UNKNOWN).getType());
    }

    @Test
    void getAmountTreasure() {
        assertEquals(0, new Case(CaseType.WATER).getAmountTreasure());
        assertEquals(0, new Case(CaseType.SAND).getAmountTreasure());
        assertEquals(0, new Case(CaseType.GRASSLAND).getAmountTreasure());
        assertEquals(0, new Case(CaseType.FOREST).getAmountTreasure());
        assertEquals(0, new Case(CaseType.ROCK).getAmountTreasure());
        assertEquals(0, new Case(CaseType.UNKNOWN).getAmountTreasure());
    }

    @Test
    void testEquals() {
        assertEquals(new Case(CaseType.WATER), new Case(CaseType.WATER));
        assertEquals(new Case(CaseType.SAND), new Case(CaseType.SAND));
        assertEquals(new Case(CaseType.GRASSLAND), new Case(CaseType.GRASSLAND));
        assertEquals(new Case(CaseType.FOREST), new Case(CaseType.FOREST));
        assertEquals(new Case(CaseType.ROCK), new Case(CaseType.ROCK));
        assertEquals(new Case(CaseType.UNKNOWN), new Case(CaseType.UNKNOWN));
    }

    @Test
    void testHashCode() {
        assertTrue(new Case(CaseType.WATER).hashCode() == new Case(CaseType.WATER).hashCode() && new Case(CaseType.WATER).equals(new Case(CaseType.WATER)));
        assertTrue(new Case(CaseType.SAND).hashCode() == new Case(CaseType.SAND).hashCode() && new Case(CaseType.SAND).equals(new Case(CaseType.SAND)));
        assertTrue(new Case(CaseType.GRASSLAND).hashCode() == new Case(CaseType.GRASSLAND).hashCode() && new Case(CaseType.GRASSLAND).equals(new Case(CaseType.GRASSLAND)));
        assertTrue(new Case(CaseType.FOREST).hashCode() == new Case(CaseType.FOREST).hashCode() && new Case(CaseType.FOREST).equals(new Case(CaseType.FOREST)));
        assertTrue(new Case(CaseType.ROCK).hashCode() == new Case(CaseType.ROCK).hashCode() && new Case(CaseType.ROCK).equals(new Case(CaseType.ROCK)));
        assertTrue(new Case(CaseType.UNKNOWN).hashCode() == new Case(CaseType.UNKNOWN).hashCode() && new Case(CaseType.UNKNOWN).equals(new Case(CaseType.UNKNOWN)));
    }

    @Test
    void isDug() {
        assertFalse(new Case(CaseType.WATER).isDug());
        assertFalse(new Case(CaseType.SAND).isDug());
        assertFalse(new Case(CaseType.GRASSLAND).isDug());
        assertFalse(new Case(CaseType.FOREST).isDug());
        assertFalse(new Case(CaseType.ROCK).isDug());
        assertFalse(new Case(CaseType.UNKNOWN).isDug());
    }

    @Test
    void dig() {
        Case c = new Case(CaseType.WATER);
        c.dig();
        assertTrue(c.isDug());
        Case c2 = new Case(CaseType.SAND);
        c2.dig();
        assertTrue(c2.isDug());
        Case c3 = new Case(CaseType.GRASSLAND);
        c3.dig();
        assertTrue(c3.isDug());
        Case c4 = new Case(CaseType.FOREST);
        c4.dig();
        assertTrue(c4.isDug());
        Case c5 = new Case(CaseType.ROCK);
        c5.dig();
        assertTrue(c5.isDug());
        Case c6 = new Case(CaseType.UNKNOWN);
        c6.dig();
        assertTrue(c6.isDug());
    }

    @Test
    void isTreasure() {
        assertFalse(new Case(CaseType.WATER).isTreasure());
        assertFalse(new Case(CaseType.SAND).isTreasure());
        assertFalse(new Case(CaseType.GRASSLAND).isTreasure());
        assertFalse(new Case(CaseType.FOREST).isTreasure());
        assertFalse(new Case(CaseType.ROCK).isTreasure());
        assertFalse(new Case(CaseType.UNKNOWN).isTreasure());
    }

    @Test
    void setOrientation() {
        assertEquals(Orientation.NONE, new Case(CaseType.WATER).getOrientation());
        assertEquals(Orientation.NONE, new Case(CaseType.SAND).getOrientation());
        assertEquals(Orientation.NONE, new Case(CaseType.GRASSLAND).getOrientation());
        assertEquals(Orientation.NONE, new Case(CaseType.FOREST).getOrientation());
        assertEquals(Orientation.NONE, new Case(CaseType.ROCK).getOrientation());
        assertEquals(Orientation.NONE, new Case(CaseType.UNKNOWN).getOrientation());
    }

    @Test
    void getOrientation() {
        assertEquals(Orientation.NONE, new Case(CaseType.WATER).getOrientation());
        assertEquals(Orientation.NONE, new Case(CaseType.SAND).getOrientation());
        assertEquals(Orientation.NONE, new Case(CaseType.GRASSLAND).getOrientation());
        assertEquals(Orientation.NONE, new Case(CaseType.FOREST).getOrientation());
        assertEquals(Orientation.NONE, new Case(CaseType.ROCK).getOrientation());
        assertEquals(Orientation.NONE, new Case(CaseType.UNKNOWN).getOrientation());
    }

    @Test
    void hasOrientation() {
        assertFalse(new Case(CaseType.WATER).hasOrientation());
        assertFalse(new Case(CaseType.SAND).hasOrientation());
        assertFalse(new Case(CaseType.GRASSLAND).hasOrientation());
        assertFalse(new Case(CaseType.FOREST).hasOrientation());
        assertFalse(new Case(CaseType.ROCK).hasOrientation());
        assertFalse(new Case(CaseType.UNKNOWN).hasOrientation());
    }
    
    @Test
    void compareToTest() {
        assertEquals(0, new Case(CaseType.WATER).compareTo(new Case(CaseType.WATER)));
        assertEquals(0, new Case(CaseType.SAND).compareTo(new Case(CaseType.SAND)));
        assertEquals(0, new Case(CaseType.GRASSLAND).compareTo(new Case(CaseType.GRASSLAND)));
        assertEquals(0, new Case(CaseType.FOREST).compareTo(new Case(CaseType.FOREST)));
        assertEquals(0, new Case(CaseType.ROCK).compareTo(new Case(CaseType.ROCK)));
        assertEquals(0, new Case(CaseType.UNKNOWN).compareTo(new Case(CaseType.UNKNOWN)));
    }
    
    @Test
    void setOrientationTest() {
        Case c = new Case(CaseType.WATER);
        c.setOrientation(Orientation.NORTH);
        assertNotNull(c.getOrientation());
    }
}