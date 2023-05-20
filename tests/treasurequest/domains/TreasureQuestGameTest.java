package treasurequest.domains;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

class TreasureQuestGameTest {
	
    private final CaseMap caseMap = new CaseMapFactory("tests/map-sample-4.txt", false).getCaseMap();
    private final TreasureQuestGame treasureQuestGame = new TreasureQuestGame(caseMap, new Player());
    
    @Test
    void caseMapTest() {
    	assertNotNull(new TreasureQuestGame(null, null));
    }
    @Test
    void testIterator() {
        assertNotNull(treasureQuestGame.iterator());
    }

    @Test
    void testGetTreasureCount() {
        assertEquals(8, treasureQuestGame.getTreasureCount());
    }

    @Test
    void testGetPlayerAmount() {
        assertEquals(16, treasureQuestGame.getPlayerAmount());
    }

    @Test
    void testGetCaseAt() {
        assertEquals("Sand", treasureQuestGame.getCaseAt(new Coordinate(1, 1)).getType());
        assertEquals("Unknown", treasureQuestGame.getCaseAt(null).getType());
    }

    @Test
    void testGetCaseCostAt() {
        assertEquals(1, treasureQuestGame.getCaseCostAt(new Coordinate(1, 1)));
        assertEquals(0, treasureQuestGame.getCaseCostAt(null));
    }

    @Test
    void testGetCaseTypeAt() {
        assertEquals("Sand", treasureQuestGame.getCaseTypeAt(new Coordinate(1, 1)));
        assertEquals(" ", treasureQuestGame.getCaseTypeAt(null));
    }

    @Test
    void testGetCoordinateActive() {
        assertEquals(new Coordinate(4, 9), treasureQuestGame.getCoordinateActive());
    }

    @Test
    void testMovePlayer() {
        treasureQuestGame.movePlayer(1, 1);
        assertEquals(new Coordinate(5, 10), treasureQuestGame.getCoordinateActive());
    }

    @Test
    void dig() {
        treasureQuestGame.dig();
    }

    @Test
    void isGameOver() {
        assertFalse(treasureQuestGame.isGameOver());
    }

    @Test
    void getGainsPlayer() {
        assertEquals(16, treasureQuestGame.getGainsPlayer());
    }

    @Test
    void getExpensesPlayer() {
        assertEquals(0, treasureQuestGame.getExpensesPlayer());
    }

    @Test
    void stopTimer() {
        treasureQuestGame.stopTimer();
    }

    @Test
    void getTimeGame() {
        assertEquals(Duration.ZERO, treasureQuestGame.getTimeGame());
    }

    @Test
    void getProfilPlayer() {
        assertNotNull(treasureQuestGame.getProfilPlayer());
    }

    @Test
    void setProfilPlayer() {
        treasureQuestGame.setProfilPlayer();
    }
}