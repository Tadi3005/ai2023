package treasurequest.domains;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

public class RandomTreasuresPlacementTest {
	
	@Test
	void randomTreasuresPlacementTest() {
		RandomTreasuresPlacement randomPlacement = new RandomTreasuresPlacement(null);
		randomPlacement.placeTreasures();
		assertNotNull(randomPlacement);
	}
	
	@Test
	void randomTreasuresPlacementTest1() {
		Map<Coordinate, Case> map = new HashMap<>();
		map.put(new Coordinate(0, 0), new Case(CaseType.SAND));
		map.put(new Coordinate(0, 1), new Case(CaseType.SAND));
		map.put(new Coordinate(0, 2), new Case(CaseType.SAND));
		map.put(new Coordinate(0, 3), new Case(CaseType.SAND));
		map.put(new Coordinate(0, 4), new Case(CaseType.SAND));
		map.put(new Coordinate(0, 5), new Case(CaseType.SAND));
		map.put(new Coordinate(0, 6), new Case(CaseType.SAND));
		RandomTreasuresPlacement randomPlacement = new RandomTreasuresPlacement(map);
		randomPlacement.placeTreasures();
		assertNotNull(randomPlacement);
	}

}
