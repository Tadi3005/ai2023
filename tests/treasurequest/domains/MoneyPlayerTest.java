package treasurequest.domains;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoneyPlayerTest {

    @Test
    void getAmount() {
        assertEquals(0, new MoneyPlayer().getAmount());
    }

    @Test
    void pay() {
        MoneyPlayer moneyPlayer = new MoneyPlayer();
        moneyPlayer.pay(10);
        assertEquals(-10, moneyPlayer.getAmount());
    }

    @Test
    void receive() {
        MoneyPlayer moneyPlayer = new MoneyPlayer();
        moneyPlayer.receive(10);
        assertEquals(10, moneyPlayer.getAmount());
    }

    @Test
    void getGains() {
        assertEquals(0, new MoneyPlayer().getGains());
    }

    @Test
    void getExpense() {
        assertEquals(0, new MoneyPlayer().getExpense());
    }

    @Test
    void setAmount() {
        MoneyPlayer moneyPlayer = new MoneyPlayer();
        moneyPlayer.setAmount(10);
        assertEquals(10, moneyPlayer.getAmount());
    }

    @Test
    void addGains() {
        MoneyPlayer moneyPlayer = new MoneyPlayer();
        moneyPlayer.addGains(10);
        assertEquals(10, moneyPlayer.getGains());
    }
}