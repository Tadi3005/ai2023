package treasurequest.domains;

import java.util.*;

/**
 * Place les trésors sur la carte de manière aléatoire
 */
public class RandomTreasuresPlacement implements TreasuresPlacement {
    private final Map<Coordinate, Case> map;

    /**
     * Constructeur de la classe RandomTreasuresPlacement
     * @param map la map de la carte du jeu
     */
    public RandomTreasuresPlacement(Map<Coordinate, Case> map) {
        this.map = map == null ? new HashMap<>() : map;
    }

    @Override
    public void placeTreasures() {
        int countTreasure = countTreasureAtStart();

        // Obtenir la liste des coordonnées de la carte
        List<Coordinate> coordinates = new ArrayList<>(map.keySet());

        // Mélanger la liste des coordonnées
        Collections.shuffle(coordinates);

        // Placer les trésors sur la carte
        for (Coordinate coord : coordinates) {
            Case currentCase = map.get(coord);
            if (currentCase.isDigable()) {
                currentCase.setAmountTreasure();
                countTreasure--;
            }
            if (countTreasure == 0) {
                break;
            }
        }
    }

    private int countTreasureAtStart() {
        int countTreasure = 0;
        for (Coordinate coord : this.map.keySet()) {
            Case currentCase = this.map.get(coord);
            if (currentCase.isDigable()) {
                countTreasure++;
            }
        }
        return (int)(countTreasure / 10.0) == 0 ? 1 : (int)(countTreasure / 10.0);
    }
}
