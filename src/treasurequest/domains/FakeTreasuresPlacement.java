package treasurequest.domains;

import java.util.HashMap;
import java.util.Map;

/**
 * Place les trésors sur la carte
 */
public class FakeTreasuresPlacement implements TreasuresPlacement {
    private final Map<Coordinate, Case> map;

    /**
     * Constructeur de la classe FakeTreasuresPlacement
     * @param map la map de la carte du jeu
     */
    public FakeTreasuresPlacement(Map<Coordinate, Case> map) {
        this.map = map == null ? new HashMap<>() : map;
    }

    /**
     * Place les trésors sur la carte
     */
    @Override
    public void placeTreasures() {
        map.get(new Coordinate(1, 1)).setAmountTreasure();
        map.get(new Coordinate(1, 2)).setAmountTreasure();
        map.get(new Coordinate(1, 3)).setAmountTreasure();
        map.get(new Coordinate(1, 4)).setAmountTreasure();
    }
}
