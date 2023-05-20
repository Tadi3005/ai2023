package treasurequest.domains;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Représente la création de l'orientation d'une case du jeu TreasureQuest
 */
public class OrientationFactory {
    private final Map<Coordinate, Case> map;

    /**
     * Constructeur de la classe OrientationFactory
     * @param map la map de la carte du jeu
     */
    public OrientationFactory(Map<Coordinate, Case> map) {
        this.map = map == null ? Map.of() : Map.copyOf(map);
        setIndices();
    }

    /**
     * Modifier la map de la carte du jeu en ajoutant l'orientation des cases
     */
    private void setIndices() {
        for (Coordinate coord : map.keySet()) {
            // Récupérer la case courante
            Case currentCase = map.get(coord);

            // Si la case est creusable et que ce n'est pas un trésor
            if (currentCase.isDigable() && !currentCase.isTreasure()) {

                // Récupérer l'ensemble des coordonnées des trésors à proximités de la case
                List<Coordinate> coordsTreasures = new ArrayList<>(getCoordsTreasuresNextTo(coord));

                // Si il y a au moins un trésor à proximité
                if (!coordsTreasures.isEmpty()) {
                    // Choisir le trésors qui est le plus proche de la case courante
                    // Si ils ont la même distance alors choisir le trésors qui a le plus de pièces
                    Coordinate closestTreasure = closestTreasure(coordsTreasures, coord);

                    // Calculer l'orientation de la case courante par rapport au trésor le plus proche
                    currentCase.setOrientation(getOrientation(closestTreasure, coord));
                }
            }
        }
    }

    /**
     * Récupérer les coordonnées du trésor le plus proche de la case courante et si ils ont la même distance alors le trésor qui a le plus de pièces
     * @param coordsTreasures liste des coordonnées des trésors
     * @param coordOfCase coordonnée de la case courante
     * @return les coordonnées du trésor le plus proche de la case courante et si ils ont la même distance alors le trésor qui a le plus de pièces
     */
    private Coordinate closestTreasure(List<Coordinate> coordsTreasures, Coordinate coordOfCase) {
        Coordinate closestTreasure = coordsTreasures.get(0);

        for (int i = 1 ; i < coordsTreasures.size() ; i++) {
            Coordinate coordTreasure = coordsTreasures.get(i);
            if (coordOfCase.distance(coordTreasure) == coordOfCase.distance(closestTreasure)) {
                if (map.get(coordTreasure).compareTo(map.get(closestTreasure)) > 0) {
                    closestTreasure = coordTreasure;
                }
                // Si les deux trésors ont le même nombre de pièces alors on prend celui qui est le plus à l'Ouest
                else if (map.get(coordTreasure).compareTo(map.get(closestTreasure)) == 0) {
                    closestTreasure = getMostWestTreasure(coordTreasure, closestTreasure);
                }
            } else if (coordOfCase.distance(coordTreasure) < coordOfCase.distance(closestTreasure)) {
                closestTreasure = coordTreasure;
            }
        }
        return closestTreasure;
    }

    private Coordinate getMostWestTreasure(Coordinate coordTreasure, Coordinate closestTreasure) {
        if (coordTreasure.getCol() < closestTreasure.getCol() && coordTreasure.getRow() <= closestTreasure.getRow()) {
            return coordTreasure;
        } else {
            return closestTreasure;
        }
    }

    /**
     * Récupérer les coordonnées des trésors à proximités de la case
     * @param coord coordonnée de la case
     * @return les coordonnées des trésors à proximités de la case
     */
    private List<Coordinate> getCoordsTreasuresNextTo(Coordinate coord) {
        List<Coordinate> coordsTreasures = new ArrayList<>();

        for (Coordinate coordinate : this.map.keySet()) {
            double distance = coord.distance(coordinate);
            // Si la case est creusable, qu'elle n'est pas la case courante et qu'elle est à une distance inférieure à 3 et que c'est un trésor
            if (map.get(coordinate).isDigable() && !coordinate.equals(coord) && distance < 3.0 && map.get(coordinate).isTreasure()) {
                // Alors ajouter la coordonnée à la liste des coordonnées des trésors
                coordsTreasures.add(coordinate);
            }
        }
        return coordsTreasures;
    }

    /**
     * Calculer l'orientation de la case courante par rapport au trésor le plus proche
     * @param coordTreasure coordonnée du trésor le plus proche
     * @param coord coordonnée de la case courante
     * @return l'orientation de la case courante par rapport au trésor le plus proche
     */
    private Orientation getOrientation(Coordinate coordTreasure, Coordinate coord) {
        int vecteurX = coordTreasure.getRow() - coord.getRow();
        int vecteurY = coordTreasure.getCol() - coord.getCol();

        return getOrientation(vecteurX, vecteurY);
    }

    /**
     * Calculer l'orientation à l'aide de deux vecteurs
     * @param vecteurX vecteur X
     * @param vecteurY vecteur Y
     * @return l'orientation à l'aide de deux vecteurs
     */
    private static Orientation getOrientation(int vecteurX, int vecteurY) {
        if (vecteurX == 0) {
            return vecteurY > 0 ? Orientation.EAST : Orientation.WEST;
        } else if (vecteurY == 0) {
            return vecteurX > 0 ? Orientation.SOUTH : Orientation.NORTH;
        } else if (vecteurX > 0) {
            return vecteurY > 0 ? Orientation.SOUTH_EAST : Orientation.SOUTH_WEST;
        } else {
            return vecteurY > 0 ? Orientation.NORTH_EAST : Orientation.NORTH_WEST;
        }
    }

    /**
     * Récupérer la map de la carte du jeu avec l'orientation de chaque case
     * @return la map de la carte du jeu avec l'orientation de chaque case
     */
    public Map<Coordinate, Case> getMapWithOrientation() {
        return map;
    }
}
