package treasurequest.domains;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ProfilPlayerFactoryTest {

    @Test
    void getProfilPlayer0() {
        Map<Coordinate, Case> caseDug = new HashMap<Coordinate, Case>();
        caseDug.put(new Coordinate(0, 0), new Case(CaseType.FOREST));
        caseDug.put(new Coordinate(0, 1), new Case(CaseType.FOREST));
        caseDug.put(new Coordinate(0, 2), new Case(CaseType.FOREST));
        caseDug.put(new Coordinate(1, 0), new Case(CaseType.FOREST));
        caseDug.put(new Coordinate(1, 1), new Case(CaseType.FOREST));
        caseDug.put(new Coordinate(4, 4), new Case(CaseType.SAND));
        caseDug.put(new Coordinate(4, 5), new Case(CaseType.SAND));
        caseDug.put(new Coordinate(4, 6), new Case(CaseType.SAND));

        ProfilPlayerFactory profilPlayerFactory = new ProfilPlayerFactory(caseDug);
        
        assertEquals(ProfilPlayer.LUMBERJACK, profilPlayerFactory.getProfilPlayer());
    }
    
    @Test
    void getProfilPlayer1() {
        ProfilPlayerFactory profilPlayerFactory = new ProfilPlayerFactory(null);
        
        assertEquals(ProfilPlayer.NONE, profilPlayerFactory.getProfilPlayer());
    }
    
    @Test
    void getProfilPlayer2() {
        Map<Coordinate, Case> caseDug = new HashMap<Coordinate, Case>();
        caseDug.put(new Coordinate(4, 4), new Case(CaseType.SAND));
        caseDug.put(new Coordinate(4, 5), new Case(CaseType.SAND));
        caseDug.put(new Coordinate(4, 6), new Case(CaseType.SAND));

        ProfilPlayerFactory profilPlayerFactory = new ProfilPlayerFactory(caseDug);
        
        assertEquals(ProfilPlayer.TOURIST, profilPlayerFactory.getProfilPlayer());
    }
    
    @Test
    void getProfilPlayer3() {
        Map<Coordinate, Case> caseDug = new HashMap<Coordinate, Case>();
        caseDug.put(new Coordinate(4, 4), new Case(CaseType.ROCK));
        caseDug.put(new Coordinate(4, 5), new Case(CaseType.ROCK));
        caseDug.put(new Coordinate(4, 6), new Case(CaseType.ROCK));

        ProfilPlayerFactory profilPlayerFactory = new ProfilPlayerFactory(caseDug);
        
        assertEquals(ProfilPlayer.MINER, profilPlayerFactory.getProfilPlayer());
    }
    
    @Test
    void getProfilPlayer4() {
        Map<Coordinate, Case> caseDug = new HashMap<Coordinate, Case>();
        caseDug.put(new Coordinate(4, 4), new Case(CaseType.GRASSLAND));
        caseDug.put(new Coordinate(4, 5), new Case(CaseType.GRASSLAND));
        caseDug.put(new Coordinate(4, 6), new Case(CaseType.GRASSLAND));

        ProfilPlayerFactory profilPlayerFactory = new ProfilPlayerFactory(caseDug);
        
        assertEquals(ProfilPlayer.FARMER, profilPlayerFactory.getProfilPlayer());
    }
}