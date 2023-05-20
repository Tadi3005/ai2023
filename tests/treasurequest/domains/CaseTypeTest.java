package treasurequest.domains;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CaseTypeTest {

    @Test
    void defineCaseType() {
        assertEquals(CaseType.SAND, CaseType.defineCaseType('S'));
        assertEquals(CaseType.GRASSLAND, CaseType.defineCaseType('P'));
        assertEquals(CaseType.FOREST, CaseType.defineCaseType('F'));
        assertEquals(CaseType.ROCK, CaseType.defineCaseType('R'));
        assertEquals(CaseType.WATER, CaseType.defineCaseType('X'));
        assertEquals(CaseType.UNKNOWN, CaseType.defineCaseType('A'));
    }

    @Test
    void getCost() {
        assertEquals(1, CaseType.SAND.getCost());
        assertEquals(2, CaseType.GRASSLAND.getCost());
        assertEquals(3, CaseType.FOREST.getCost());
        assertEquals(5, CaseType.ROCK.getCost());
        assertEquals(0, CaseType.WATER.getCost());
        assertEquals(0, CaseType.UNKNOWN.getCost());
    }

    @Test
    void getType() {
        assertEquals("Sand", CaseType.SAND.getType());
        assertEquals("Grassland", CaseType.GRASSLAND.getType());
        assertEquals("Forest", CaseType.FOREST.getType());
        assertEquals("Rock", CaseType.ROCK.getType());
        assertEquals("Water", CaseType.WATER.getType());
        assertEquals("Unknown", CaseType.UNKNOWN.getType());
    }
}