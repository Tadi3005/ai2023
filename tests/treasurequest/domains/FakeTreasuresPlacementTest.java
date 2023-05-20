package treasurequest.domains;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class FakeTreasuresPlacementTest {

    private final Map<Coordinate, Case> map = new HashMap<>(
            Map.of(
                    new Coordinate(1, 1), new Case(CaseType.SAND),
                    new Coordinate(1, 2), new Case(CaseType.SAND),
                    new Coordinate(1, 3), new Case(CaseType.SAND),
                    new Coordinate(1, 4), new Case(CaseType.SAND),
                    new Coordinate(1, 5), new Case(CaseType.SAND),
                    new Coordinate(1, 6), new Case(CaseType.SAND)
            )
    );
    private final FakeTreasuresPlacement fakePlacement = new FakeTreasuresPlacement(map);

    @Test
    void placeTreasuresTest() {
    	fakePlacement.placeTreasures();
        assertTrue(map.get(new Coordinate(1, 1)).isTreasure());
        assertTrue(map.get(new Coordinate(1, 2)).isTreasure());
        assertTrue(map.get(new Coordinate(1, 3)).isTreasure());
        assertTrue(map.get(new Coordinate(1, 4)).isTreasure());
        assertFalse(map.get(new Coordinate(1, 5)).isTreasure());
        assertFalse(map.get(new Coordinate(1, 6)).isTreasure());
    }
}