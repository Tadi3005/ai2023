package treasurequest.domains;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CaseMapTest {
    CaseMap caseMap = new CaseMap(
            Map.of(
                    new Coordinate(0, 0), new Case(CaseType.SAND),
                    new Coordinate(0, 1), new Case(CaseType.SAND),
                    new Coordinate(0, 2), new Case(CaseType.SAND),
                    new Coordinate(1, 0), new Case(CaseType.SAND),
                    new Coordinate(1, 1), new Case(CaseType.SAND),
                    new Coordinate(1, 2), new Case(CaseType.SAND),
                    new Coordinate(2, 0), new Case(CaseType.SAND),
                    new Coordinate(2, 1), new Case(CaseType.SAND),
                    new Coordinate(2, 2), new Case(CaseType.SAND)
            )
    );
    
    CaseMap caseMapNull = new CaseMap(null);
    
    @Test
    void caseMapNullTest() {
    	assertNotNull(caseMapNull.getCaseAt(new Coordinate(0, 0)));
    }
    
    @Test
    void testIterator() {
        assertNotNull(caseMap.iterator());
    }

    @Test
    void testGetCoordinateMiddle() {
        assertEquals(new Coordinate(1, 1), caseMap.getCoordinateMiddle());
    }

    @Test
    void testGetCaseAt() {
        assertEquals(new Case(CaseType.SAND), caseMap.getCaseAt(new Coordinate(0, 0)));
        assertEquals(new Case(CaseType.UNKNOWN), caseMapNull.getCaseAt(null));
    }

    @Test
    void testGetCaseCostAt() {
        assertEquals(1, caseMap.getCaseCostAt(new Coordinate(0, 0)));
        assertEquals(0, caseMapNull.getCaseCostAt(null));
    }

    @Test
    void testGetCaseTypeFor() {
        assertEquals("Sand", caseMap.getCaseTypeFor(new Coordinate(0, 0)));
        assertEquals(" ", caseMap.getCaseTypeFor(null));
    }

    @Test
    void testGetTreasureCount() {
        assertEquals(0, caseMap.getTreasureCount());
    }

    @Test
    void testRemoveTreasure() {
        caseMap.removeTreasure(new Coordinate(0, 0));
        assertEquals(0, caseMap.getTreasureCount());
    }

    @Test
    void canDigWith() {
        assertTrue(caseMap.canDigWith(1));
        assertFalse(caseMap.canDigWith(0));
    }
}