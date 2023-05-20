package treasurequest.domains;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;

public class TreasureQuestGameFactoryTest {
	@Test
	void treasureQuestGameFactoryTest() {
		assertNotNull(new TreasureQuestGameFactory("tests/map-sample.txt", new Player()));
		assertNotNull(new TreasureQuestGameFactory(null, null));
		
		var tgf = new TreasureQuestGameFactory("tests/map-sample-4.txt", new Player());
		tgf.createGame();
		assertNotNull(tgf.getCurentGame());
		
		var tgf2 = new TreasureQuestGameFactory("tests/map-sample-4.txt", new Player());
		tgf2.createRandomGame();
		assertNotNull(tgf.getCurentGame());
	}
}
