package treasurequest.domains;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    Player player = new Player();
    @Test
    void addCaseDig() {
        player.addCaseDig(new Coordinate(1, 1), new Case(CaseType.SAND));
    }

    @Test
    void getAmount() {
        assertEquals(0, player.getAmount());
    }

    @Test
    void move() {
        player.move(1, 1);
    }

    @Test
    void getCoordinate() {
        assertEquals(new Coordinate(0, 0), player.getCoordinate());
    }

    @Test
    void pay() {
        player.pay(10);
        assertEquals(-10, player.getAmount());
    }

    @Test
    void receive() {
        player.receive(10);
        assertEquals(10, player.getAmount());
    }

    @Test
    void getGains() {
        assertEquals(0, player.getGains());
    }

    @Test
    void getExpense() {
        assertEquals(0, player.getExpense());
    }

    @Test
    void setAmount() {
        player.setAmount(10);
        assertEquals(10, player.getAmount());
    }

    @Test
    void setCoordinate() {
        player.setCoordinate(new Coordinate(1, 1));
        assertEquals(new Coordinate(1, 1), player.getCoordinate());
    }

    @Test
    void time() {
        player.startTime();
        player.stopTime();
    }

    @Test
    void getTimeGame() {
        assertNotNull(player.getTimeGame());
    }

    @Test
    void addGains() {
        player.addGains(10);
        assertEquals(10, player.getGains());
    }

    @Test
    void setProfilPlayer() {
        player.setProfilPlayer();
        assertEquals(ProfilPlayer.NONE, player.getProfilPlayer());
    }

    @Test
    void getProfilPlayer() {
        assertEquals(ProfilPlayer.NONE, player.getProfilPlayer());
    }
}